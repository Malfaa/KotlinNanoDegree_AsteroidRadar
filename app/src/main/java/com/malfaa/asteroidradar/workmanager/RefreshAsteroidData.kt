package com.malfaa.asteroidradar.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.malfaa.asteroidradar.room.AsteroidDatabase
import com.malfaa.asteroidradar.room.Repository

class RefreshAsteroidData(context: Context, params: WorkerParameters): CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "RefreshAsteroidData"
    }

    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.getInstance(applicationContext)
        val repository = Repository(database.dao)

        return try {
            repository.insertNewAsteroids()
            Result.success()
        }catch (e:Exception){
            Result.retry()
        }
    }
}