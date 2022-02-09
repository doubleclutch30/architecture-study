package com.lutawav.architecturestudy.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseViewModel
import com.lutawav.architecturestudy.ui.ViewType
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread

class MovieViewModel(
    override val repository: NaverSearchRepositoryImpl
) : BaseViewModel<Movie>(repository) {

    private val _isLoading = MutableLiveData(false)
    override val _data = MutableLiveData<List<Movie>>()

    val data: LiveData<List<Movie>> get() = _data
    val isLoading: LiveData<Boolean> get() = _isLoading

    val viewType = Transformations.map(data) { list ->
        if (list.isNotEmpty()) {
            ViewType.VIEW_SEARCH_RESULT
        } else {
            ViewType.VIEW_SEARCH_NO_RESULT
        }
    }

    override fun init() {
        repository.getLatestMovieResult()
            .compose(singleIoMainThread())
            .doOnSubscribe { _isLoading.value = true }
            .doAfterTerminate { _isLoading.value = false }
            .subscribe({
                keyword.value = it.keyword
                _data.value = it.movies
            }, { e ->
                val message = e.message ?: return@subscribe
                _errorMsg.value = message

            })
            .addTo(compositeDisposable)

    }

    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword
        )
            .compose(singleIoMainThread())
            .doOnSubscribe { _isLoading.value = true }
            .doAfterTerminate { _isLoading.value = false }
            .subscribe({ movieRepo ->
                _data.value = movieRepo.movies

            }, { e ->
                val message = e.message ?: return@subscribe
                _errorMsg.value = message
            })
            .addTo(compositeDisposable)
    }
}