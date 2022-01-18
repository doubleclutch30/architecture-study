package com.lutawav.architecturestudy.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
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

    override fun bind(movie: Movie) {

        binding.movieTitle.text =
            HtmlCompat.fromHtml(movie.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.movieDirector.text = movie.director
        binding.movieActor.text = movie.actor

        Glide.with(binding.movieImage.context)
            .load(movie.image)
            .into(binding.movieImage)

        binding.userRating.rating = movie.userRating

        binding.root.setOnClickListener { view ->
            view.startWebView(movie.link)
        }
    }
}
