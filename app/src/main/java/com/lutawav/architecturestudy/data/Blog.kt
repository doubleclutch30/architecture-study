package com.lutawav.architecturestudy.data

import com.google.gson.annotations.SerializedName

data class Blog(
    @SerializedName("bloggerlink")
    val bloggerLink: String,
    @SerializedName("bloggername")
    val bloggerName: String,
    val description: String,
    val link: String,
    val postdate: String,
    val title: String
)
