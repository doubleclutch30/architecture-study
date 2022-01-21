package com.lutawav.architecturestudy.ui.image

import com.lutawav.architecturestudy.data.repository.NaverSearchRepository
import com.lutawav.architecturestudy.ui.BaseSearchPresenter

class ImagePresenter(
    override val view: ImageContract.View,
    override val repository: NaverSearchRepository
) : BaseSearchPresenter(view, repository), ImageContract.Presenter {

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword
        )
            .subscribe({ responseImage ->
                val images = responseImage.images
                if (images.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(responseImage.images)
            }, { e ->
                handleError(e)
            }
        )
    }
}