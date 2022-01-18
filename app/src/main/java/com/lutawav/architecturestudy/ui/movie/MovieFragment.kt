package com.lutawav.architecturestudy.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.data.model.NaverQueryResponse
import com.lutawav.architecturestudy.data.repository.NaverSearchRepository
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.databinding.FragmentMovieBinding
import com.lutawav.architecturestudy.network.NaverApi
import com.lutawav.architecturestudy.ui.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : BaseFragment<FragmentMovieBinding>() {

    private lateinit var movieAdapter: MovieAdapter

    override fun getViewBinding(): FragmentMovieBinding =
        FragmentMovieBinding.inflate(layoutInflater)

    private val naverSearchRepository by lazy { NaverSearchRepositoryImpl() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        movieAdapter = MovieAdapter()
        binding.movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = movieAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun bindViews() {
        binding.searchButton.setOnClickListener {
            val keyword = binding.searchEditText.text.toString().trim()
            if (keyword.isBlank()) {
                Toast.makeText(activity, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                search(keyword)
            }
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