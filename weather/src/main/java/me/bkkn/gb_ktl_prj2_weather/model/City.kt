package me.bkkn.gb_ktl_prj2_weather.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    var city: String?,
    val lat: Double,
    val lon: Double

) : Parcelable