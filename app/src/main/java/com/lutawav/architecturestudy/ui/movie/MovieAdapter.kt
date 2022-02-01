package com.lutawav.architecturestudy.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.databinding.ItemMovieBinding
import com.lutawav.architecturestudy.ui.BaseAdapter
import com.lutawav.architecturestudy.ui.BaseViewHolder
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
) : BaseViewHolder<Movie>(binding) {

    override fun bind(item: Movie) {
        binding.movie = item
        binding.executePendingBindings()

        binding.root.setOnClickListener { view ->
            view.startWebView(item.link)
        }
    }
}
