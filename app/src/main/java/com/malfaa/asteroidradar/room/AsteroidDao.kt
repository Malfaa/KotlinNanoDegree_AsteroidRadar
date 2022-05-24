package com.malfaa.asteroidradar.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.malfaa.asteroidradar.Data
import kotlinx.coroutines.flow.Flow


@Dao
interface AsteroidDao {
    @Query("SELECT * FROM ${Data.ENTITY}")
    fun listOfAsteroids():Flow<List<Asteroid>>

    @Delete  //todo don't know if i use this or not, possibly there is no need to remove because it fetchs data from the internet
    suspend fun removeAsteroidData(): Asteroid
}