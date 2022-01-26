package com.lutawav.architecturestudy.ui.blog

import android.util.Log
import com.lutawav.architecturestudy.data.database.entity.BlogEntity
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseSearchPresenter
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread
import com.lutawav.architecturestudy.util.then

class BlogPresenter(
    override val view: BlogContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), BlogContract.Presenter {

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword,
        )
            .subscribe({ responseBlog ->
                val blogs = responseBlog.blogs
                if (blogs.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(responseBlog.blogs)
            }, { e ->
                handleError(e)
            }
        )
    }
}