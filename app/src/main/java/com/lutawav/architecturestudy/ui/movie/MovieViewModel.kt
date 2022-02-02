package com.lutawav.architecturestudy.ui.movie

import android.util.Log
import androidx.databinding.ObservableField
import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseViewModel
import com.lutawav.architecturestudy.ui.ViewType
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread

class MovieViewModel(
    override val repository: NaverSearchRepositoryImpl
) : BaseViewModel<Movie>(repository) {
    override val data: ObservableField<List<Movie>> = ObservableField()

    override fun init() {
        repository.getLatestMovieResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.set(it.keyword)
                data.set(it.movies)
            }, { e ->
                val message = e.message ?: return@subscribe
                Log.e("movie", message)
            })
            .addTo(compositeDisposable)
    }

    override fun search(keyword: String) {
        Log.e("movie", keyword)

        repository.getMovie(
            keyword = keyword
        )
            .compose(singleIoMainThread())
            .subscribe({ movieRepo ->
                viewType.set(
                    if (movieRepo.movies.isEmpty()) {
                        ViewType.VIEW_SEARCH_NO_RESULT
                    } else {
                        ViewType.VIEW_SEARCH_SUCCESS
                    }
                )
                data.set(movieRepo.movies)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }
}