package com.lutawav.architecturestudy.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lutawav.architecturestudy.data.repository.NaverSearchRepository
import com.lutawav.architecturestudy.util.SingleLiveEvent
import com.lutawav.architecturestudy.util.hideKeyboard
import com.lutawav.architecturestudy.util.then
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T>(
    protected open val repository: NaverSearchRepository
) : ViewModel() {
    val keyword = MutableLiveData<String>()

    protected val _errorMsg = SingleLiveEvent<String>()
    private val _invalidKeyword = SingleLiveEvent<Boolean>()

    val errorMsg: LiveData<String> get() = _errorMsg
    val invalidKeyword: LiveData<Boolean> get() = _invalidKeyword

    protected val compositeDisposable = CompositeDisposable()

    private val debounceTime: Long = 600L
    private var lastClickTime: Long = 0

    abstract val _data: MutableLiveData<List<T>>

    abstract fun search(keyword: String)

    abstract fun init()

    fun onClick(v: View, keyword: String) {
        if (System.currentTimeMillis() - lastClickTime < debounceTime) {
            return
        }

        _invalidKeyword.value = keyword.isBlank()
        keyword.isNotBlank().then {
            v.hideKeyboard()
            search(keyword)
        }
    }

    public override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

enum class ViewType(type: Int) {
    VIEW_SEARCH_RESULT(0),
    VIEW_SEARCH_NO_RESULT(1),
    ;
}