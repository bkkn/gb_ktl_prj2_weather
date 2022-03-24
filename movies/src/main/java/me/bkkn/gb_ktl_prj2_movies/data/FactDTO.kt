package me.bkkn.gb_ktl_prj2_movies.data

import com.google.gson.annotations.SerializedName

data class FactDTO(
    @SerializedName("original_title")
    val title: String?,
    @SerializedName("vote_average")
    val rating: String?
)
