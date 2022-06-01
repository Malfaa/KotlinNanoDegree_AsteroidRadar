package com.malfaa.asteroidradar.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.malfaa.asteroidradar.PictureOfDay
import com.malfaa.asteroidradar.network.AsteroidApi
import com.malfaa.asteroidradar.network.asAsteroidDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val dao: AsteroidDao) {

//    suspend fun getAllAsteroids(): List<Asteroid> {
//        return withContext(Dispatchers.IO) {
//             dao.listOfAsteroids()
//        }
//    }

    val allAsteroids : LiveData<List<Asteroid>> = Transformations.map(dao.listOfAsteroids()){
        it.asAsteroidDomain()
    }

    suspend fun insertNewAsteroids(){ //todo see where can i insert this func
        withContext(Dispatchers.IO){
            val asteroids = AsteroidApi.getAsteroidsFromNetwork()
            dao.adicionarAsteroidData(asteroids)//.asAsteroidDomain()
        }
    }

    suspend fun getAPOD(): PictureOfDay{
        return withContext(Dispatchers.IO){
            AsteroidApi.getPictureOfTheDay()
        }
    }
}