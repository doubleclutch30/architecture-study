package com.lutawav.architecturestudy.data.model

import com.google.gson.annotations.SerializedName
import com.lutawav.architecturestudy.data.database.entity.MovieEntity

data class Movie (
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val director: String,
    val actor: String,
    val pubDate: String,
    @SerializedName("userRating")
    private val _userRating: String
) {
    val userRating: Float
        get() = _userRating.toFloat() / 2
}

data class ResponseMovie(
    val movies: List<Movie>
)

data class MovieRepo(
    val keyword: String,
    val movies: List<Movie>
)

data class MovieLocalData(
    val movies: List<MovieEntity>
)