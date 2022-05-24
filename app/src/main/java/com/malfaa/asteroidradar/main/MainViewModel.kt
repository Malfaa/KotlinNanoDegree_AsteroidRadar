package com.malfaa.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.malfaa.asteroidradar.room.Asteroid
import com.malfaa.asteroidradar.room.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _asteroidsList = MutableLiveData<List<Asteroid>>()
    val asteroidList: LiveData<List<Asteroid>>
        get() = _asteroidsList

    private val _asteroidClicked = MutableLiveData<Boolean>()
    val asteroidClicked : LiveData<Boolean>
        get() = _asteroidClicked

    private val _asteroidId = MutableLiveData<Asteroid>()
    val asteroidId : LiveData<Asteroid>
        get() = _asteroidId

//    fun setDataListAsteroids(){ fixme fix here and repo
//        viewModelScope.launch {
//            _asteroidsList.value = repository.getAllAsteroids()
//        }
//    }


    fun onAsteroidClicked(){
        _asteroidClicked.value = true
    }

    fun onAsteroidClickedReturned(){
        _asteroidClicked.value = false
    }
}