package me.bkkn.gb_ktl_prj2_movies.data

sealed class AppState {
    data class Success(val film: Film) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
