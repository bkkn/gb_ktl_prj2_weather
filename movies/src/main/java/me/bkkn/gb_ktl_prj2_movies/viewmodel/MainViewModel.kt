package me.bkkn.gb_ktl_prj2_movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bkkn.gb_ktl_prj2_movies.data.AppState
import me.bkkn.gb_ktl_prj2_movies.data.FilmRepository
import me.bkkn.gb_ktl_prj2_movies.data.IRepository

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: IRepository = FilmRepository()
) : ViewModel() {

    fun getLiveData() : MutableLiveData<AppState>{
        return liveDataToObserve
    }
}