package com.lutawav.architecturestudy.data

import com.google.gson.annotations.SerializedName

data class NaverQueryResponse<T>(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<T>
)