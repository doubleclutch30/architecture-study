package com.lutawav.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lutawav.architecturestudy.R
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.data.source.local.NaverSearchLocalDataSourceImpl
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.lutawav.architecturestudy.util.showToastMessage
import com.lutawav.architecturestudy.util.then

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel<*>>(
    private val layoutId: Int,
) : Fragment() {

    lateinit var binding: T

    abstract val viewModel: VM

    val naverSearchRepository by lazy {
        NaverSearchRepositoryImpl(
            naverSearchRemoteDataSource = NaverSearchRemoteDataSourceImpl,
            naverSearchLocalDataSource = NaverSearchLocalDataSourceImpl
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initModelCallBack()
    }

    private fun initModelCallBack() {
        viewModel.errorMsg.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val message = viewModel.errorMsg.get() ?: return
                viewModel.errorMsg.set(null)
                showErrorMessage(message)
            }
        })

        viewModel.invalidKeyword.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val invalid = viewModel.invalidKeyword.get() ?: return
                invalid.then {
                    context?.let {
                        showErrorMessage(it.getString(R.string.warn_input_keyword))
                    }
                    viewModel.invalidKeyword.set(false)
                }
            }
        })
    }

    fun showErrorMessage(message: String) {
        context?.showToastMessage(message)
    }
}

