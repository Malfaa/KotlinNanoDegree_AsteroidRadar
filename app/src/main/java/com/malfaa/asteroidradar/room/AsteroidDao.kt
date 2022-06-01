package com.malfaa.asteroidradar.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.malfaa.asteroidradar.DataConstants
import kotlinx.coroutines.flow.Flow


@Dao
interface AsteroidDao {
    @Query("SELECT * FROM ${DataConstants.ENTITY} ORDER BY closeApproachDate")
    fun listOfAsteroids() : List<Asteroid>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun adicionarAsteroidData(list: List<Asteroid>)
}