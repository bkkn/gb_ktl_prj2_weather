package me.bkkn.gb_ktl_prj2_weather.data

class Repository : IRepository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorage(): Weather {
        return Weather()
    }
}