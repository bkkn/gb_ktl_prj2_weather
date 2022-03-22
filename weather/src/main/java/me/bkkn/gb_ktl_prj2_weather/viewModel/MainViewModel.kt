package com.als.gblesson2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bkkn.gb_ktl_prj2_weather.data.AppState
import me.bkkn.gb_ktl_prj2_weather.data.IRepository
import me.bkkn.gb_ktl_prj2_weather.data.Repository
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: IRepository = Repository()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSource() = getDataFromLocalSource()

    fun getWeatherFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.postValue(AppState.Loading)
        Thread {
            sleep(1000)
            val rnd = Random.nextBoolean();
            if (rnd)
                liveDataToObserve.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
            else
                liveDataToObserve.postValue(AppState.Error(Throwable()))
        }.start()
    }


}