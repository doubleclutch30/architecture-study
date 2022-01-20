package com.lutawav.architecturestudy.ui.movie

import com.lutawav.architecturestudy.data.repository.NaverSearchRepository
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseSearchContract
import com.lutawav.architecturestudy.ui.BaseSearchPresenter

class MoviePresenter(
    override val view: MovieContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), MovieContract.Presenter {

    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword,
            success = { responseMovie ->
                view.updateResult(responseMovie.movies)
            },
            fail = { e ->
                handleError(e)
            }
        )
    }
}