package com.malfaa.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.malfaa.asteroidradar.R
import com.malfaa.asteroidradar.databinding.AsteroidItemBinding
import com.malfaa.asteroidradar.room.Asteroid

class MainAdapter(private val clickListener: AsteroidListener): ListAdapter<Asteroid, MainAdapter.ViewHolder>(DiffCallback()){

    class ViewHolder(private val binding: AsteroidItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Asteroid, clickListener: AsteroidListener){
            binding.asteroid = item
            binding.hazardImage.apply {
                when(item.isPotentiallyHazardous){ //todo test
                    true -> this.setImageResource(R.drawable.ic_status_potentially_hazardous)
                    false -> this.setImageResource(R.drawable.ic_status_normal)

                }                }
            binding.clickListener = clickListener
            binding.executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AsteroidItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class DiffCallback : DiffUtil.ItemCallback<Asteroid>(){
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }
    }

    class AsteroidListener(val clickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }

}