package com.lutawav.architecturestudy.ui.movie

import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseSearchPresenter

class MoviePresenter(
    override val view: MovieContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), MovieContract.Presenter {

    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword
        )
            .subscribe({ responseMovie ->
                val movies = responseMovie.movies
                if (movies.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(responseMovie.movies)
            }, { e ->
                handleError(e)
            }
        )
    }
}