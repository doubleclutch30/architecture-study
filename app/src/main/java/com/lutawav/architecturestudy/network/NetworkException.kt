package com.lutawav.architecturestudy.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.lutawav.architecturestudy.util.peekBody
import okhttp3.Response

class NetworkException(
    val statusCode: Int,
    val code: String,
    override val message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    companion object {
        fun create(response: Response): NetworkException? {
            val body = response.peekBody()
                ?: return null

            try {
                Gson().fromJson(body, NaverErrorBody::class.java)?.let {
                    return NetworkException(response.code, it.errorCode, it.errorMessage)
                }
            } catch (e: Exception) {
                Log.e("NetworkException", "error=${e}")
            }
            return null
        }
    }
}

data class NaverErrorBody(
    @SerializedName("errorCode") val errorCode: String,
    @SerializedName("errorMessage") val errorMessage: String
)
