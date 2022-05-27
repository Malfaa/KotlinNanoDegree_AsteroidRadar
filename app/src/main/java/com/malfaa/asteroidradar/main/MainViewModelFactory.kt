package com.malfaa.asteroidradar.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.malfaa.asteroidradar.room.Repository

class MainViewModelFactory(private val repo : Repository): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repo) as T
        }
        throw IllegalArgumentException("Viewmodel desconhecido")
    }
}