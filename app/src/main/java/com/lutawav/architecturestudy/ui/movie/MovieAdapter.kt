package com.lutawav.architecturestudy.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.databinding.ItemMovieBinding
import com.lutawav.architecturestudy.ui.BaseAdapter
import com.lutawav.architecturestudy.ui.BaseViewHolder
import com.lutawav.architecturestudy.ui.OnItemClickListener
import com.lutawav.architecturestudy.util.startWebView

internal class MovieAdapter : BaseAdapter<Movie, MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

internal class MovieViewHolder(
    private val binding: ItemMovieBinding
) : BaseViewHolder<Movie>(binding), OnItemClickListener {

    init {
        binding.click = this
    }

    override fun bind(item: Movie) {
        binding.movie = item
        binding.executePendingBindings()
    }

    override fun onClick(v: View, url: String) {
        v.startWebView(url)
    }
}
