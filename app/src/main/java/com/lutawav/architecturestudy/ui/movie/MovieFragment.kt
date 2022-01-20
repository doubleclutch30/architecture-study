package com.lutawav.architecturestudy.ui.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.databinding.FragmentMovieBinding
import com.lutawav.architecturestudy.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : BaseFragment<FragmentMovieBinding>(), MovieContract.View{

    override val presenter: MovieContract.Presenter by lazy {
        MoviePresenter(this, naverSearchRepository)
    }

    private lateinit var movieAdapter: MovieAdapter

    override fun getViewBinding(): FragmentMovieBinding =
        FragmentMovieBinding.inflate(layoutInflater)

    private val naverSearchRepository by lazy {
        NaverSearchRepositoryImpl()
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

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Movie>) {
        movieAdapter.setData(result)
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}