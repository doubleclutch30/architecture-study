package com.lutawav.architecturestudy.ui.image

import android.util.Log
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseSearchPresenter
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread
import com.lutawav.architecturestudy.util.then

class ImagePresenter(
    override val view: ImageContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), ImageContract.Presenter {

    override fun subscribe() {
        val lastKeyword = repository.getLatestImageKeyword()
        lastKeyword.isNotBlank().then {
            repository.getLatestImageResult()
                .compose(singleIoMainThread())
                .subscribe({
                    view.updateUi(lastKeyword, it)
                }, { e ->
                    val message = e.message ?: return@subscribe
                    Log.e("image", message)
                })
                .addTo(disposable)
        }
    }

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword
        )
            .flatMap {
                repository.refreshImageSearchHistory(
                    keyword = keyword,
                    images = it.images
                )
            }
            .compose(singleIoMainThread())
            .subscribe({ images ->
                if (images.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(images)
            }, { e ->
                handleError(e)
            })
            .addTo(disposable)
    }

}