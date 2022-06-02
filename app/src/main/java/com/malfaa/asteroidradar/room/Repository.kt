package com.malfaa.asteroidradar.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.malfaa.asteroidradar.PictureOfDay
import com.malfaa.asteroidradar.todayAsteroidsFormatter
import com.malfaa.asteroidradar.network.AsteroidApi
import com.malfaa.asteroidradar.network.asAsteroidDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val dao: AsteroidDao) {

    val allAsteroids : LiveData<List<Asteroid>> = Transformations.map(dao.listOfAsteroids()){
        it.asAsteroidDomain()
    }

    val allTodayAsteroids : LiveData<List<Asteroid>> = Transformations.map(dao.listOfTodayAsteroids(
        todayAsteroidsFormatter()
    )){
        it.asAsteroidDomain()
    }

    suspend fun insertNewAsteroids(){
        withContext(Dispatchers.IO){
            val asteroids = AsteroidApi.getAsteroidsFromNetwork()
            dao.adicionarAsteroidData(asteroids)
        }
    }

    suspend fun getAPOD(): PictureOfDay{
        return withContext(Dispatchers.IO){
            AsteroidApi.getPictureOfTheDay()
        }
    }

    suspend fun deletePreviousAsteroids(){
        return withContext(Dispatchers.IO){
            dao.deletePreviousAsteroids()
        }
    }
}