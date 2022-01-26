package com.lutawav.architecturestudy.ui

import com.lutawav.architecturestudy.data.repository.NaverSearchRepository
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable

abstract class BaseSearchPresenter(
    protected open val view: BaseSearchContract.View<*, *>,
    protected open val repository: NaverSearchRepository
) : BaseSearchContract.Presenter {

    protected val disposable = CompositeDisposable()

    override fun unsubscribe() {
        disposable.clear()
    }

    override fun handleError(e: Throwable) {
        val message = e.message ?: return
        view.showErrorMessage(message)
    }
}