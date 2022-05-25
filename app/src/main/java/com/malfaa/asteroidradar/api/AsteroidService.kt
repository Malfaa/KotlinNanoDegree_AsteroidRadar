package com.malfaa.asteroidradar.api

import com.malfaa.asteroidradar.Constants
import com.malfaa.asteroidradar.room.Asteroid
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface AsteroidService {
    @GET(Constants.API)
    fun getAsteroids(): Call<List<Asteroid>>
}

object AsteroidApi {
    val retrofitService : AsteroidService by lazy { retrofit.create(AsteroidService::class.java) }
}

//fun List<Asteroid>.asAsteroidDomain():List<Asteroid>{
//    return map{
//        Asteroid(
//            id = it.id,
//            codename = it.codename,
//            closeApproachDate = it.closeApproachDate,
//            absoluteMagnitude = it.absoluteMagnitude,
//            estimatedDiameter = it.estimatedDiameter,
//            relativeVelocity = it.relativeVelocity,
//            distanceFromEarth = it.distanceFromEarth,
//            isPotentiallyHazardous = it.isPotentiallyHazardous
//        )
//    }
//}