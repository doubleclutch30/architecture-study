package com.lutawav.architecturestudy.ui.blog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.R
import com.lutawav.architecturestudy.data.model.Blog
import com.lutawav.architecturestudy.databinding.FragmentBlogBinding
import com.lutawav.architecturestudy.ui.BaseFragment
import com.lutawav.architecturestudy.ui.BaseSearchContract
import com.lutawav.architecturestudy.util.then

class BlogFragment : BaseFragment<FragmentBlogBinding>(
    R.layout.fragment_blog
), BlogContract.View {

    override val presenter: BlogContract.Presenter by lazy {
        BlogPresenter(this, naverSearchRepository)
    }

    private lateinit var blogAdapter: BlogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override var viewType: BaseSearchContract.ViewType =
        BaseSearchContract.ViewType.VIEW_SEARCH_BEFORE
        set(value) {
            if (field != value) {
                field = value
                binding.viewType = value
                binding.invalidateAll()
            }
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

    override fun updateUi(keyword: String, blogs: List<Blog>) {
        keyword.isNotBlank().then {
            binding.searchBar.keyword = keyword

            viewType = when {
                blogs.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
                else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
            }

            blogs.isNotEmpty().then {
                blogAdapter.setData(blogs)
            }

            binding.invalidateAll()
        }
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Blog>) {
        viewType = when {
            result.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
            else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
        }

        if (result.isEmpty()) {
            blogAdapter.clear()
        } else {
            blogAdapter.setData(result)
        }

        binding.invalidateAll()
    }
}