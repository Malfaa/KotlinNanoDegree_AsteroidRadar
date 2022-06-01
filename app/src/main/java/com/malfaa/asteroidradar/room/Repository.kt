package com.malfaa.asteroidradar.room

import com.malfaa.asteroidradar.PictureOfDay
import com.malfaa.asteroidradar.network.AsteroidApi
import com.malfaa.asteroidradar.network.asAsteroidDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val dao: AsteroidDao) {

    suspend fun getAllAsteroids(): List<Asteroid> {
        return withContext(Dispatchers.IO) {
             dao.listOfAsteroids()
        }
    }

    suspend fun insertAllAsteroids(){ //todo see where can i insert this func
        withContext(Dispatchers.IO){
            val asteroids = AsteroidApi.getAllAsteroids()
            dao.adicionarAsteroidData(asteroids)//.asAsteroidDomain()
        }
    }

    suspend fun getAPOD(): PictureOfDay{
        return withContext(Dispatchers.IO){
            AsteroidApi.getPictureOfTheDay()
        }
    }
}