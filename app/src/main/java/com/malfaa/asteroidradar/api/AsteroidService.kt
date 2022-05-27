package com.malfaa.asteroidradar.api

import com.malfaa.asteroidradar.Constants
import com.malfaa.asteroidradar.PictureOfDay
import com.malfaa.asteroidradar.room.Asteroid
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object AsteroidApi {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()

    private val retrofitService : AsteroidService by lazy { retrofit.create(AsteroidService::class.java) }

    fun getPictureOfTheDay() = retrofitService.getPictureDay(Constants.API_KEY)

    fun getAllAsteroids(): List<Asteroid> {
        val getStringJson = retrofitService.getAsteroids("", "", Constants.API_KEY)
        val jsonObject = JSONObject(getStringJson)
        return parseAsteroidsJsonResult(jsonObject)
    }
}

interface AsteroidService {
    @GET(Constants.HTTP_GET_ASTEROIDS_PATH)
    fun getAsteroids(
        @Query( Constants.START_DATE_PARAM)
        startDate: String,
        @Query( Constants.END_DATE_PARAM)
        endDate: String,
        @Query( Constants.API_KEY_PARAM)
        apiKey: String
    ): String

    @GET(Constants.HTTP_APOD_PATH)
    fun getPictureDay(
        @Query(Constants.API_KEY_PARAM)
        apiKey: String
    ): PictureOfDay
}
