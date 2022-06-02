package com.malfaa.asteroidradar.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.malfaa.asteroidradar.Constants

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM ${Constants.ENTITY} ORDER BY closeApproachDate")
    fun listOfAsteroids() : LiveData<List<Asteroid>>

    @Query("SELECT * FROM ${Constants.ENTITY} WHERE closeApproachDate = :date ORDER BY closeApproachDate ASC")
    fun listOfTodayAsteroids(date: String) : LiveData<List<Asteroid>>

    @Query("DELETE FROM ${Constants.ENTITY} WHERE closeApproachDate < DATE()")
    suspend fun deletePreviousAsteroids()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun adicionarAsteroidData(list: List<Asteroid>)
}