package com.malfaa.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malfaa.asteroidradar.PictureOfDay
import com.malfaa.asteroidradar.api.AsteroidApi
import com.malfaa.asteroidradar.room.Asteroid
import com.malfaa.asteroidradar.room.Repository
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _asteroidsList = MutableLiveData<List<Asteroid>>()
    val asteroidList: LiveData<List<Asteroid>>
        get() = _asteroidsList

    private val _navigateAsteroid = MutableLiveData<Asteroid?>()
    val navigateAsteroid
        get() = _navigateAsteroid

    private val _apod = MutableLiveData<PictureOfDay>()
    val apod: LiveData<PictureOfDay>
        get() = _apod


    fun onAsteroidItemClick(id: Asteroid){
        _navigateAsteroid.value = id
    }

//    fun setDataListAsteroids(){ fixme fix here and repo
//        viewModelScope.launch {
//            _asteroidsList.value = repository.getAllAsteroids()
//        }
//    }

//    private fun retrieveOnlineData(){
//        AsteroidApi.retrofitService.getAsteroids().enqueue(object : Callback<String> {
//
//        })
//    }

    private fun getAPOD(){
        viewModelScope.launch {
            try {
                _apod.value = repository.getAPOD()
            }catch (e: Exception){
                Log.e("Error", e.toString())
            }
        }
    }


    fun onAsteroidNavigated(){
        _navigateAsteroid.value = null
    }

}

// TODO: DIA 1 -> VER API RESTFUL E ENTENDER O CONCEITO NOVAMENTE
// TODO: DIA 2 -> IMPLEMENTAR A API E COLOCAR CACHING NO DATABASE ROOM