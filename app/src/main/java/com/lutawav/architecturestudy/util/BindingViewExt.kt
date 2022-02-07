package com.lutawav.architecturestudy.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lutawav.architecturestudy.ui.BaseAdapter

@BindingAdapter("htmlText")
fun TextView.setText(title: String) {
    text = HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String) {
    try {
        Log.e("ImageViewExt", "url=${url}")
        Glide.with(this)
            .load(url)
            .into(this)
    } catch (e: Exception) {
        Log.e("ImageViewExt", "error=${e.message}")
    }
}

@BindingAdapter("setItems")
fun RecyclerView.setItems(items: List<Any>?) {
    with((adapter as BaseAdapter<Any, *>)) {
        items?.let { setData(it) }
    }
}
