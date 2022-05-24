package com.malfaa.asteroidradar.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.malfaa.asteroidradar.Data
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Data.ENTITY)
data class Asteroid(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
    ) : Parcelable