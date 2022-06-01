package com.malfaa.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.malfaa.asteroidradar.R
import com.malfaa.asteroidradar.databinding.FragmentMainBinding
import com.malfaa.asteroidradar.room.AsteroidDatabase
import com.malfaa.asteroidradar.room.Repository


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var factory: MainViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentMainBinding.inflate(inflater)

//        val application = requireNotNull(this.activity).application
        val dataSource = AsteroidDatabase.getInstance(/*application*/requireContext()).dao
        factory = MainViewModelFactory(Repository(dataSource) )
        viewModel = ViewModelProvider(this, this.factory)[MainViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MainAdapter(MainAdapter.AsteroidListener { asteroidId ->
            viewModel.onAsteroidToDetailArguments(asteroidId) // FIXME: corrigir aqui
        })

        binding.asteroidRecycler.adapter = adapter

        viewModel.listOfAsteroids.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.navigateToAsteroidDetail.observe(viewLifecycleOwner){
            asteroid ->
            findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid!!))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
