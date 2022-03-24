package me.bkkn.gb_ktl_prj2_movies.data

data class Film(
    val title: String = getDefaultFilmTitle(),
    val genre: String = "genre",
    val description: String = "desciption",
    val rating : Int = 5
)

fun getDefaultFilmTitle(): String {
    return "Some Film Name"
}
