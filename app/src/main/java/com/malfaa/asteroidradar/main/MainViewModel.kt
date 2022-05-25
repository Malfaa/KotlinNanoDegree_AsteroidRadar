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

    private val _navigateAsteroid = MutableLiveData<Asteroid?>()
    val navigateAsteroid
        get() = _navigateAsteroid

    fun onAsteroidItemClick(id: Asteroid){
        _navigateAsteroid.value = id
    }

//    fun setDataListAsteroids(){ fixme fix here and repo
//        viewModelScope.launch {
//            _asteroidsList.value = repository.getAllAsteroids()
//        }
//    }


    fun onAsteroidNavigated(){
        _navigateAsteroid.value = null
    }

}

// TODO: DIA 1 -> VER API RESTFUL E ETENTENDER O CONCEITO NOVAMENTE
// TODO: DIA 2 -> IMPLEMENTAR A API E COLOCAR CACHING NO DATABASE ROOM