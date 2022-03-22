package me.bkkn.gb_ktl_prj2_weather.data

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = -3
)

fun getDefaultCity() = City("Москва", 55.755826, 37.617299900000035)

