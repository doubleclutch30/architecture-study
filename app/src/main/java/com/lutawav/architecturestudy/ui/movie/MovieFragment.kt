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
import com.lutawav.architecturestudy.ui.BaseSearchContract
import com.lutawav.architecturestudy.util.then

class MovieFragment : BaseFragment<FragmentMovieBinding>(
    R.layout.fragment_movie
), MovieContract.View {

    override val presenter: MovieContract.Presenter by lazy {
        MoviePresenter(this, naverSearchRepository)
    }

    private lateinit var movieAdapter: MovieAdapter

    override var viewType: BaseSearchContract.ViewType = BaseSearchContract.ViewType.VIEW_SEARCH_BEFORE
        set(value) {
            if (field != value) {
                field = value
                binding.viewType = field
                binding.invalidateAll()
            }
        }

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

            movies.isNotEmpty().then {
                movieAdapter.setData(movies)
            }

            viewType = when {
                movies.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
                else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
            }

            binding.invalidateAll()
        }
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Movie>) {
        viewType = when {
            result.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
            else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
        }

        if (result.isEmpty()) {
            movieAdapter.clear()
        } else {
            movieAdapter.setData(result)
        }

        binding.invalidateAll()
    }
}