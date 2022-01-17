package com.lutawav.architecturestudy.data

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    val display: Int,
    @SerializedName("items")
    val images: List<Image>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
