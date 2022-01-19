package com.lutawav.architecturestudy.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.databinding.FragmentMovieBinding
import com.lutawav.architecturestudy.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : BaseFragment<FragmentMovieBinding>() {

    private lateinit var movieAdapter: MovieAdapter

    override fun getViewBinding(): FragmentMovieBinding =
        FragmentMovieBinding.inflate(layoutInflater)

    private val naverSearchRepository by lazy { NaverSearchRepositoryImpl() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        movieAdapter = MovieAdapter()
        binding.movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = movieAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }
    }

    override fun search(keyword: String) {
        naverSearchRepository.getMovie(
            keyword = keyword,
            success = { responseMovie ->
                movieAdapter.setData(responseMovie.movies)
            },
            fail = { e ->
                Log.e("Movie", "error=${e.message}")
            }
        )
    }
}