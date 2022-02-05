package com.lutawav.architecturestudy.ui.blog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lutawav.architecturestudy.data.model.Blog
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseViewModel
import com.lutawav.architecturestudy.ui.ViewType
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread

class BlogViewModel(
    override val repository: NaverSearchRepositoryImpl
) : BaseViewModel<Blog>(repository) {

    override val _data: MutableLiveData<List<Blog>> = MutableLiveData()
    val data: LiveData<List<Blog>>
        get() = _data

    val viewType = Transformations.map(data) { list ->
        if (list.isNotEmpty()) {
            ViewType.VIEW_SEARCH_RESULT
        } else {
            ViewType.VIEW_SEARCH_NO_RESULT
        }
    }

    override fun init() {
        repository.getLatestBlogResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.value = it.keyword
                _data.value = it.blogs
            }, { e ->
                val message = e.message ?: return@subscribe
                Log.e("blog", message)
            })
            .addTo(compositeDisposable)
    }

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword
        )
            .compose(singleIoMainThread())
            .subscribe({ blogRepo ->
                _data.value = blogRepo.blogs
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.value = message
            })
            .addTo(compositeDisposable)
    }
}