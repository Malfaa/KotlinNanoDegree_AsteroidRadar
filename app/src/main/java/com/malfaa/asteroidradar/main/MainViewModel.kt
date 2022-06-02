package com.malfaa.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malfaa.asteroidradar.PictureOfDay
import com.malfaa.asteroidradar.room.Asteroid
import com.malfaa.asteroidradar.room.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val listOfAsteroids = repository.allAsteroids

    val todayListOfAsteroids = repository.allTodayAsteroids

    private val _navigateToAsteroidDetail = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetail: LiveData<Asteroid?>
        get() = _navigateToAsteroidDetail

    private val _astronomicalPictureOfDay = MutableLiveData<PictureOfDay>()
    val astronomicalPictureOfDay: LiveData<PictureOfDay>
        get() = _astronomicalPictureOfDay


    fun onAsteroidToDetailArguments(id: Asteroid){
        _navigateToAsteroidDetail.value = id
    }

    init {
        getAPOD()
        insertAsteroids()
    }

    private fun insertAsteroids(){
        viewModelScope.launch {
            try{
                repository.insertNewAsteroids()
            }catch (e:Exception){
                Log.e("Error on Asteroids INSE", e.toString())
            }
        }
    }

    private fun getAPOD(){
        viewModelScope.launch {
            try {
                _astronomicalPictureOfDay.value = repository.getAPOD()
            }catch (e: Exception){
                Log.e("Error on APOD", e.toString())
            }
        }
    }

    fun navigationTerminated(){
        _navigateToAsteroidDetail.value = null
    }

}