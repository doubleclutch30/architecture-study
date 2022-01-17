package com.lutawav.architecturestudy.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val display: Int,
    val lastBuildDate: String,
    val start: Int,
    val total: Int,
    @SerializedName("items")
    val movies: List<Movie>
)