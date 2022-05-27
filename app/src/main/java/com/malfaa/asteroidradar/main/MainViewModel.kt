package com.malfaa.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malfaa.asteroidradar.PictureOfDay
import com.malfaa.asteroidradar.room.Asteroid
import com.malfaa.asteroidradar.room.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _listOfAsteroids = MutableLiveData<List<Asteroid>>()
    val listOfAsteroids: LiveData<List<Asteroid>>
        get() = _listOfAsteroids

    private val _navigateToAsteroidDetail = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetail
        get() = _navigateToAsteroidDetail

    private val _astronomicalPictureOfDay = MutableLiveData<PictureOfDay>()
    val astronomicalPictureOfDay: LiveData<PictureOfDay>
        get() = _astronomicalPictureOfDay


    fun onAsteroidToDetailArguments(id: Asteroid){
        _navigateToAsteroidDetail.value = id
    }

    fun getAsteroids(){
        viewModelScope.launch {
            try{
                _listOfAsteroids.value = repository.teste()
            }catch (e:Exception){
                Log.e("Error on Asteroids", e.toString())
            }
        }
    }

    fun getAPOD(){
        viewModelScope.launch {
            try {
                _astronomicalPictureOfDay.value = repository.getAPOD()
            }catch (e: Exception){
                Log.e("Error on APOD", e.toString())
            }
        }
    }


    fun onAsteroidNavigated(){
        _navigateToAsteroidDetail.value = null
    }

}

// TODO: DIA 1 -> VER API RESTFUL E ENTENDER O CONCEITO NOVAMENTE
// TODO: DIA 2 -> IMPLEMENTAR A API E COLOCAR CACHING NO DATABASE ROOM