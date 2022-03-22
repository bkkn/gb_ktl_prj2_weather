package me.bkkn.gb_ktl_prj2_movies.data

interface IRepository {
    fun getFilmFromServer(): Film
    fun getFilmFromLocalStorage(): Film
}