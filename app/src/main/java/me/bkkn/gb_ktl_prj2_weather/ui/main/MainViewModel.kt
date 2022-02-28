package me.bkkn.gb_ktl_prj2_weather.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData<Any>()
) : ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getDataFromLocalSource() {
        Thread {
            sleep(3000)
            liveDataToObserve.postValue(Any())
        }.start()
    }
}