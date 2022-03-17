package me.bkkn.gb_ktl_prj2_movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bkkn.gb_ktl_prj2_movies.data.AppState
import me.bkkn.gb_ktl_prj2_movies.data.FilmRepository
import me.bkkn.gb_ktl_prj2_movies.data.IRepository
import kotlin.random.Random

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: IRepository = FilmRepository()
) : ViewModel() {

    fun getLiveData() : MutableLiveData<AppState>{
        return liveDataToObserve
    }

    fun getFilmFromLocalSource() = getDataFromLocalSource()

    fun getFilmFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.postValue(AppState.Loading)
        Thread {
            Thread.sleep(1000)
            val rnd = Random.nextBoolean();
            if (rnd)
                liveDataToObserve.postValue(AppState.Success(repository.getFilmFromLocalStorage()))
            else
                liveDataToObserve.postValue(AppState.Error(Throwable()))
        }.start()
    }
}