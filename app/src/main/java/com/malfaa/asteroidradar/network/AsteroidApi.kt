package com.malfaa.asteroidradar.network

import com.malfaa.asteroidradar.Constants
import com.malfaa.asteroidradar.room.Asteroid
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object AsteroidApi {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()

    private val retrofitService : AsteroidService by lazy { retrofit.create(AsteroidService::class.java) }

    suspend fun getPictureOfTheDay() = retrofitService.getPictureDay(Constants.API_KEY)

    suspend fun getAsteroidsFromNetwork(): List<Asteroid> {
        val getStringJson : String = retrofitService.getAsteroids("", "", Constants.API_KEY)
        val jsonObject = JSONObject(getStringJson)
        return parseAsteroidsJsonResult(jsonObject)
    }
}