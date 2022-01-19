package com.lutawav.architecturestudy.ui.blog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.databinding.FragmentBlogBinding
import com.lutawav.architecturestudy.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie.*

class BlogFragment : BaseFragment<FragmentBlogBinding>() {


    private lateinit var blogAdapter: BlogAdapter

    override fun getViewBinding(): FragmentBlogBinding =
        FragmentBlogBinding.inflate(layoutInflater)

    private val naverSearchRepository by lazy { NaverSearchRepositoryImpl() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        blogAdapter = BlogAdapter()
        binding.blogRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = blogAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }
    }

    override fun search(keyword: String) {
        naverSearchRepository.getBlog(
            keyword = keyword,
            success = { responseImage ->
                blogAdapter.setData(responseImage.blogs)
            },
            fail = { e ->
                Log.e("Image", "error=${e.message}")
            }
        )
    }
}