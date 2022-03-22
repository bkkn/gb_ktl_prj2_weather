package me.bkkn.gb_ktl_prj2_weather.data

interface IRepository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}