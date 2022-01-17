package com.lutawav.architecturestudy.data

data class MovieResponse(
    val display: Int,
    val lastBuildDate: String,
    val start: Int,
    val total: Int,
    val movies: List<Movie>
)