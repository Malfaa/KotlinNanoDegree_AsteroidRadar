package com.malfaa.asteroidradar.room

import androidx.lifecycle.LiveData
import com.malfaa.asteroidradar.PictureOfDay
import com.malfaa.asteroidradar.api.AsteroidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(private val dao: AsteroidDao) {

    suspend fun getAllAsteroids(): Flow<List<Asteroid>> {
        return withContext(Dispatchers.IO) {
             dao.listOfAsteroids()
        } // FIXME: fix here
    }

    suspend fun teste(): List<Asteroid>{
        return withContext(Dispatchers.IO) {
             AsteroidApi.getAllAsteroids()
        }
    }

//    suspend fun insertAllAsteroids(list: List<Asteroid>){
//        withContext(Dispatchers.IO){
//            dao.adicionarAsteroidData(AsteroidApi.getAllAsteroids())
//        }
//    }

    suspend fun getAPOD(): PictureOfDay{
        return withContext(Dispatchers.IO){
            AsteroidApi.getPictureOfTheDay()
        }
    }
}