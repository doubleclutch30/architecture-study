package com.lutawav.architecturestudy.ui.blog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.R
import com.lutawav.architecturestudy.databinding.FragmentBlogBinding
import com.lutawav.architecturestudy.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlogFragment : BaseFragment<FragmentBlogBinding, BlogViewModel>(R.layout.fragment_blog) {

    override val viewModel: BlogViewModel by viewModel()

    private lateinit var blogAdapter: BlogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        blogAdapter = BlogAdapter()

        binding.blogRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = blogAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        binding.vm = viewModel
        binding.lifecycleOwner = this

        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onCleared()
    }

}