package com.lutawav.architecturestudy.data

import com.google.gson.annotations.SerializedName

data class BlogResponse(
    val display: Int,
    @SerializedName("items")
    val blogs: List<Blog>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)