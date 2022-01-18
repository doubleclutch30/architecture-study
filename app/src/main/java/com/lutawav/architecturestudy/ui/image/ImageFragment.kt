package com.lutawav.architecturestudy.ui.image

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.data.Image
import com.lutawav.architecturestudy.data.NaverQueryResponse
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        imageAdapter = ImageAdapter()
        binding.imageRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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
        NaverApi.getImage(keyword)
            .enqueue(object : Callback<NaverQueryResponse<Image>> {
                override fun onResponse(
                    call: Call<NaverQueryResponse<Image>>,
                    response: Response<NaverQueryResponse<Image>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        imageAdapter.setData(body.items)
                    }
                }

                override fun onFailure(call: Call<NaverQueryResponse<Image>>, t: Throwable) {
                    Log.e("Movie", "error=${t.message}")
                }
            })
    }


}