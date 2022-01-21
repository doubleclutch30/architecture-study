package com.lutawav.architecturestudy.ui.blog

import com.lutawav.architecturestudy.data.repository.NaverSearchRepository
import com.lutawav.architecturestudy.ui.BaseSearchPresenter

class BlogPresenter(
    override val view: BlogContract.View,
    override val repository: NaverSearchRepository
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