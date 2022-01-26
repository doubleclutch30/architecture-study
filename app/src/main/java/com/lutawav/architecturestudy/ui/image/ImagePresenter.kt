package com.lutawav.architecturestudy.ui.image

import android.util.Log
import com.lutawav.architecturestudy.data.database.entity.ImageEntity
import com.lutawav.architecturestudy.data.repository.NaverSearchRepository
import com.lutawav.architecturestudy.ui.BaseSearchPresenter
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImagePresenter(
    override val view: ImageContract.View,
    override val repository: NaverSearchRepository
) : BaseSearchPresenter(view, repository), ImageContract.Presenter {

    override fun subscribe() {
        super.subscribe()
        loadData()
    }

    private fun loadData() {
        val lastKeyword = repository.getLatestImageKeyword()
        if (lastKeyword.isBlank()) {
            view.updateUi(lastKeyword, emptyList())
        } else {
            repository.getLatestImageResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
            .map {
                updateImageResult { repository.clearImageResult() }
                it.images.isNotEmpty().then {
                    val imageList = arrayListOf<ImageEntity>()
                    it.images.mapTo(imageList) { image -> ImageEntity(
                            link = image.link,
                            sizeWidth = image.sizeWidth,
                            sizeHeight = image.sizeHeight,
                            thumbnail = image.thumbnail,
                            title = image.title
                        )
                    }
                    updateImageResult {
                        repository.saveImageResult(imageList)
                    }
                }
                repository.saveImageKeyword(keyword)
                it.images
            }
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
            }
        )
    }
}