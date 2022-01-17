package com.lutawav.architecturestudy.data

import com.google.gson.annotations.SerializedName

data class Movie (
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val director: String,
    val actor: String,
    val pubDate: String,
    @SerializedName("userRating")
    private val userRating: String
)
