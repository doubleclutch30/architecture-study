package com.lutawav.architecturestudy.ui.image

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.databinding.FragmentImageBinding
import com.lutawav.architecturestudy.ui.BaseFragment
import com.lutawav.architecturestudy.util.then
import kotlinx.android.synthetic.main.fragment_movie.*

class ImageFragment : BaseFragment<FragmentImageBinding>(), ImageContract.View {

    override val presenter: ImageContract.Presenter by lazy {
        ImagePresenter(this, naverSearchRepository)
    }

    private lateinit var imageAdapter: ImageAdapter

    override fun getViewBinding(): FragmentImageBinding =
        FragmentImageBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        imageAdapter = ImageAdapter()
        binding.imageRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = imageAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }

        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateUi(keyword: String, images: List<Image>) {
        keyword.isNotEmpty().then {
            binding.searchBar.keyword = keyword

            if (images.isEmpty()) {
                hideResultListView()
                showEmptyResultView()
            } else {
                hideEmptyResultView()
                showResultListView()
                imageAdapter.setData(images)
            }
        }
    }

    override fun showEmptyResultView() {
        binding.emptyResultView.visibility = View.VISIBLE
    }

    override fun showResultListView() {
        binding.imageRecyclerView.visibility = View.VISIBLE
    }

    override fun hideEmptyResultView() {
        binding.emptyResultView.visibility = View.GONE
    }

    override fun hideResultListView() {
        binding.imageRecyclerView.visibility = View.GONE
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Image>) {
        imageAdapter.setData(result)
    }
}