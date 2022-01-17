package com.lutawav.architecturestudy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.R
import com.lutawav.architecturestudy.data.MovieResponse
import com.lutawav.architecturestudy.databinding.FragmentMovieBinding
import com.lutawav.architecturestudy.network.NaverApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var binding: FragmentMovieBinding? = null
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        search("바람")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =FragmentMovieBinding.inflate(inflater, container, false)
        .also { binding = it}
        .root

    private fun initViews() {
        binding?.movieRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = MovieAdapter()
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }


    private fun search(query: String) {
        NaverApi.getMovies(query)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        movieAdapter.setItems(body.movies)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("Movie", "error=${t.message}")
                }
            })
    }



}