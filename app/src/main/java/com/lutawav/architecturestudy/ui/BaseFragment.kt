package com.lutawav.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.data.source.local.NaverSearchLocalDataSourceImpl
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.lutawav.architecturestudy.util.showToastMessage

abstract class BaseFragment<VB: ViewBinding>: Fragment(), BaseContract.View {

    lateinit var binding: VB

    abstract fun getViewBinding(): VB

    val naverSearchRepository by lazy {
        NaverSearchRepositoryImpl(
            naverSearchRemoteDataSource = NaverSearchRemoteDataSourceImpl,
            naverSearchLocalDataSource = NaverSearchLocalDataSourceImpl
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    abstract fun search(keyword: String)

    override fun showErrorMessage(message: String) {
        context?.showToastMessage(message)
    }
}