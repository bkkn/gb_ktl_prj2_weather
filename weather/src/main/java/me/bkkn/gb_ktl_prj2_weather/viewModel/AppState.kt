package me.bkkn.gb_ktl_prj2_weather.viewModel

import me.bkkn.gb_ktl_prj2_weather.model.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
