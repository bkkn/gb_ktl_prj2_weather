package me.bkkn.gb_ktl_prj2_weather.model

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("fact")
    val factical: FactDTO?
)