package com.lutawav.architecturestudy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.R
import com.lutawav.architecturestudy.data.BlogResponse
import com.lutawav.architecturestudy.data.ImageResponse
import com.lutawav.architecturestudy.databinding.FragmentBlogBinding
import com.lutawav.architecturestudy.databinding.FragmentImageBinding
import com.lutawav.architecturestudy.network.NaverApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageFragment : Fragment(R.layout.fragment_image) {

    private var binding: FragmentImageBinding? = null
    private lateinit var imageAdapter: ImageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentImageBinding.inflate(inflater, container, false)
        .also { binding = it}
        .root

    private fun initViews() {
        imageAdapter = ImageAdapter()
        binding?.imageRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = imageAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun bindViews() {
        binding?.searchButton?.setOnClickListener {
            val keyword = binding?.searchEditText?.text.toString().trim()
            if (keyword.isBlank()) {
                Toast.makeText(activity, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                search(keyword)
            }
        }
    }

    private fun search(query: String) {
        NaverApi.getImage(query)
            .enqueue(object : Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        imageAdapter.setItems(body.images)
                    }
                }

                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                    Log.e("Movie", "error=${t.message}")
                }
            })
    }
}