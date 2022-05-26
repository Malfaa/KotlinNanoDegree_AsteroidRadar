package com.malfaa.asteroidradar.room

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

    suspend fun getAPOD(): PictureOfDay{
        return withContext(Dispatchers.IO){
            AsteroidApi.getPictureOfTheDay()
        }
    }

    //Alter later on to receive from the Network
}