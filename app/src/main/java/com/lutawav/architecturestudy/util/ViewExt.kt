package com.lutawav.architecturestudy.util

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BindingAdapter
import com.lutawav.architecturestudy.ui.WebViewActivity
import com.lutawav.architecturestudy.ui.WebViewActivity.Companion.EXTRA_URL

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.startWebView(url: String) {
    Intent(context, WebViewActivity::class.java).apply {
        putExtra(EXTRA_URL, url)
    }.run {
        context.startActivity(this)
    }
}