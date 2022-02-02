package com.lutawav.architecturestudy.ui.blog

import android.util.Log
import androidx.databinding.ObservableField
import com.lutawav.architecturestudy.data.model.Blog
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseViewModel
import com.lutawav.architecturestudy.ui.ViewType
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread

class BlogViewModel(
    override val repository: NaverSearchRepositoryImpl
) : BaseViewModel<Blog>(repository){

    override val data: ObservableField<List<Blog>> = ObservableField()

    override fun init() {
        repository.getLatestBlogResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.set(it.keyword)
                data.set(it.blogs)
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
            .flatMap {
                repository.refreshBlogSearchHistory(
                    keyword = keyword,
                    blogs = it.blogs
                )
            }
            .compose(singleIoMainThread())
            .subscribe({ blogRepo ->
                viewType.set(
                    if (blogRepo.blogs.isEmpty()) {
                        ViewType.VIEW_SEARCH_NO_RESULT
                    } else {
                        ViewType.VIEW_SEARCH_SUCCESS
                    }
                )
                data.set(blogRepo.blogs)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }
}