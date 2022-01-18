package com.lutawav.architecturestudy.ui.image

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.data.model.NaverQueryResponse
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.databinding.FragmentImageBinding
import com.lutawav.architecturestudy.network.NaverApi
import com.lutawav.architecturestudy.ui.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageFragment : BaseFragment<FragmentImageBinding>() {

    private lateinit var imageAdapter: ImageAdapter

    override fun getViewBinding(): FragmentImageBinding =
        FragmentImageBinding.inflate(layoutInflater)

    private val naverSearchRepository by lazy { NaverSearchRepositoryImpl() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        imageAdapter = ImageAdapter()
        binding.imageRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = imageAdapter
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
        naverSearchRepository.getImage(
            keyword = keyword,
            success = { responseImage ->
                imageAdapter.setData(responseImage.images)
            },
            fail = { e ->
                Log.e("Image", "error=${e.message}")
            }
        )
    }


}