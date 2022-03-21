package me.bkkn.gb_ktl_prj2_movies.data

class FilmRepository : IRepository {
    override fun getFilmFromServer(): Film {
        return Film()
    }

    override fun getFilmFromLocalStorage(): Film {
        return Film()
    }
}