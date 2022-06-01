package com.malfaa.asteroidradar.network

import com.malfaa.asteroidradar.Constants
import com.malfaa.asteroidradar.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidService {
    @GET(Constants.HTTP_GET_ASTEROIDS_PATH)
    suspend fun getAsteroids(
        @Query( Constants.START_DATE_PARAM)
        startDate: String,
        @Query( Constants.END_DATE_PARAM)
        endDate: String,
        @Query( Constants.API_KEY_PARAM)
        apiKey: String
    ): String

    @GET(Constants.HTTP_APOD_PATH)
    suspend fun getPictureDay(
        @Query(Constants.API_KEY_PARAM)
        apiKey: String
    ): PictureOfDay
}
