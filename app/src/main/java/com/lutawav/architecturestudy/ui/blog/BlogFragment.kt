package com.lutawav.architecturestudy.ui.blog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.data.model.Blog
import com.lutawav.architecturestudy.databinding.FragmentBlogBinding
import com.lutawav.architecturestudy.ui.BaseFragment
import com.lutawav.architecturestudy.util.then

class BlogFragment : BaseFragment<FragmentBlogBinding>(), BlogContract.View {

    override val presenter: BlogContract.Presenter by lazy {
        BlogPresenter(this, naverSearchRepository)
    }

    private lateinit var blogAdapter: BlogAdapter

    override fun getViewBinding(): FragmentBlogBinding =
        FragmentBlogBinding.inflate(layoutInflater)


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

        binding.searchBar.onClickAction = { keyword ->
            search(keyword)
        }

        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateUi(keyword: String, blog: List<Blog>) {
        keyword.isNotEmpty().then {
            binding.searchBar.keyword = keyword

            if (blog.isEmpty()) {
                hideResultListView()
                showEmptyResultView()
            } else {
                hideEmptyResultView()
                showResultListView()
                blogAdapter.setData(blog)
            }
        }
    }

    override fun showEmptyResultView() {
        binding.emptyResultView.visibility = View.VISIBLE
    }

    override fun showResultListView() {
        binding.blogRecyclerView.visibility = View.VISIBLE
    }

    override fun hideEmptyResultView() {
        binding.emptyResultView.visibility = View.GONE
    }

    override fun hideResultListView() {
        binding.blogRecyclerView.visibility = View.GONE
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Blog>) {
        if (result.isEmpty()) {
            blogAdapter.clear()
        } else {
            blogAdapter.setData(result)
        }
    }
}