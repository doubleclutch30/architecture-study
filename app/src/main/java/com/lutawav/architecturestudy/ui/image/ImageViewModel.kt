package com.lutawav.architecturestudy.ui.image

import android.util.Log
import androidx.databinding.ObservableField
import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseViewModel
import com.lutawav.architecturestudy.ui.ViewType
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread

class ImageViewModel(
    override val repository: NaverSearchRepositoryImpl
) : BaseViewModel<Image>(repository) {

    override val data: ObservableField<List<Image>> = ObservableField()

    override fun init() {
        repository.getLatestImageResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.set(it.keyword)
                data.set(it.images)
            }, { e ->
                val message = e.message ?: return@subscribe
                Log.e("image", message)
            })
            .addTo(compositeDisposable)
    }

    override fun search(keyword: String) {
        repository.getImage(
            keyword = keyword
        )
            .compose(singleIoMainThread())
            .subscribe({ imageRepo ->
                viewType.set(
                    if (imageRepo.images.isEmpty()) {
                        ViewType.VIEW_SEARCH_NO_RESULT
                    } else {
                        ViewType.VIEW_SEARCH_SUCCESS
                    }
                )
                data.set(imageRepo.images)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }
}