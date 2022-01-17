package com.lutawav.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lutawav.architecturestudy.data.Movie
import com.lutawav.architecturestudy.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var data = arrayListOf<Movie>()

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(movie : Movie) {

                binding.movieTitle.text = HtmlCompat.fromHtml(movie.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                binding.movieDirector.text = movie.director
                binding.movieActor.text = movie.actor

                Glide.with(binding.movieImage.context)
                    .load(movie.image)
                    .into(binding.movieImage)

                binding.userRating.rating = movie.userRating


                binding.root.setOnClickListener {

                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setItems(items: List<Movie>) {
        data.addAll(items)
        notifyDataSetChanged()
    }
}
