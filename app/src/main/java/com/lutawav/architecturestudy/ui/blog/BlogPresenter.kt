package com.lutawav.architecturestudy.ui.blog

import android.util.Log
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseSearchPresenter
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread
import com.lutawav.architecturestudy.util.then

class BlogPresenter(
    override val view: BlogContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), BlogContract.Presenter {

    override fun subscribe() {
        val lastKeyword = repository.getLatestBlogKeyword()
        lastKeyword.isNotBlank().then {
            repository.getLatestBlogResult()
                .compose(singleIoMainThread())
                .subscribe({
                    view.updateUi(lastKeyword, it)
                }, { e ->
                    val message = e.message ?: return@subscribe
                    Log.e("blog", message)
                })
                .addTo(disposable)
        }
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
            .subscribe({ blogs ->
                if (blogs.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(blogs)
            }, { e ->
                handleError(e)
            })
            .addTo(disposable)
    }
}