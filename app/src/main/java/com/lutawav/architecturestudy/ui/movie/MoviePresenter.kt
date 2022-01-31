package com.lutawav.architecturestudy.ui.movie

import android.util.Log
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseSearchPresenter
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread

class MoviePresenter(
    override val view: MovieContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), MovieContract.Presenter {

    override fun subscribe() {
        repository.getLatestMovieResult()
            .compose(singleIoMainThread())
            .subscribe({
                view.updateUi(it.keyword, it.movies)
            }, { e ->
                val message = e.message ?: return@subscribe
                Log.e("movie", message)
            })
            .addTo(disposable)
    }

    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword
        )
            .flatMap {
                repository.refreshMovieSearchHistory(
                    keyword = keyword,
                    movies = it.movies
                )
            }
            .compose(singleIoMainThread())
            .subscribe({ movieRepo ->
                val movies = movieRepo.movies
                if (movies.isEmpty()) {

                } else {

                }
                view.updateResult(movies)
            }, { e ->
                handleError(e)
            })
            .addTo(disposable)
    }
}