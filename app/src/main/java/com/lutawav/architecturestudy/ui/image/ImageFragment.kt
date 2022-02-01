package com.lutawav.architecturestudy.ui.image

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.R
import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.databinding.FragmentImageBinding
import com.lutawav.architecturestudy.ui.BaseFragment
import com.lutawav.architecturestudy.ui.BaseSearchContract
import com.lutawav.architecturestudy.util.then

class ImageFragment : BaseFragment<FragmentImageBinding>(
    R.layout.fragment_image,
), ImageContract.View {

    override val presenter: ImageContract.Presenter by lazy {
        ImagePresenter(this, naverSearchRepository)
    }

    private lateinit var imageAdapter: ImageAdapter

    override var viewType: BaseSearchContract.ViewType =
        BaseSearchContract.ViewType.VIEW_SEARCH_BEFORE
        set(value) {
            if (field != value) {
                field = value
                binding.viewType = value
                binding.invalidateAll()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        imageAdapter = ImageAdapter()
        binding.imageRecyclerView.apply {
            adapter = imageAdapter
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

    override fun updateUi(keyword: String, images: List<Image>) {
        keyword.isNotBlank().then {
            binding.searchBar.keyword = keyword

            viewType = when {
                images.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
                else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
            }

            images.isNotEmpty().then {
                imageAdapter.setData(images)
            }

            binding.invalidateAll()
        }
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Image>) {
        viewType = when {
            result.isEmpty() -> BaseSearchContract.ViewType.VIEW_SEARCH_NO_RESULT
            else -> BaseSearchContract.ViewType.VIEW_SEARCH_SUCCESS
        }

        if (result.isEmpty()) {
            imageAdapter.clear()
        } else {
            imageAdapter.setData(result)
        }

        binding.invalidateAll()
    }
}