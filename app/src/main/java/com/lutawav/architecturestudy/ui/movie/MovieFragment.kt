package com.lutawav.architecturestudy.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.R
import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.databinding.FragmentMovieBinding
import com.lutawav.architecturestudy.ui.BaseFragment
import com.lutawav.architecturestudy.util.then

class MovieFragment : BaseFragment<FragmentMovieBinding>(
    R.layout.fragment_movie
), MovieContract.View {

    override val presenter: MovieContract.Presenter by lazy {
        MoviePresenter(this, naverSearchRepository)
    }

    private lateinit var movieAdapter: MovieAdapter


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

        binding.searchBar.onClickAction = { keyword ->
            search(keyword)
        }

        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateUi(keyword: String, movies: List<Movie>) {
        keyword.isNotBlank().then {
            binding.searchBar.keyword = keyword

            if (movies.isEmpty()) {
                hideResultListView()
                showEmptyResultView()
                Log.e("updateUi", "empty")
            } else {
                hideEmptyResultView()
                showResultListView()
                movieAdapter.setData(movies)
                Log.e("updateUi", "ok")
            }
        }
    }

    override fun showEmptyResultView() {
        binding.emptyResultView.visibility = View.VISIBLE
    }

    override fun showResultListView() {
        binding.movieRecyclerView.visibility = View.VISIBLE
    }

    override fun hideEmptyResultView() {
        binding.emptyResultView.visibility = View.GONE
    }

    override fun hideResultListView() {
        binding.movieRecyclerView.visibility = View.GONE
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Movie>) {
        if (result.isEmpty()) {
            movieAdapter.clear()
        } else {
            movieAdapter.setData(result)
        }
    }
}