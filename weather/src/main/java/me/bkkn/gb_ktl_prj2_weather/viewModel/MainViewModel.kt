package me.bkkn.gb_ktl_prj2_weather.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bkkn.gb_ktl_prj2_weather.data.AppState
import me.bkkn.gb_ktl_prj2_weather.data.IRepository
import me.bkkn.gb_ktl_prj2_weather.data.Repository
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val mutableLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: IRepository = Repository()
) :
    ViewModel() {

    val liveData: LiveData<AppState> get() = mutableLiveData

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)

    fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)

    private fun getDataFromLocalSource(isRussian: Boolean) {
        mutableLiveData.postValue(AppState.Loading)
        Thread {
            sleep(1000)
            mutableLiveData.postValue(
                AppState.Success(
                    if (isRussian) repository.getWeatherFromLocalStorageRus()
                    else repository.getWeatherFromLocalStorageWorld()
                )
            )
        }.start()
    }
}