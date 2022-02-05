package com.lutawav.architecturestudy.ui.image

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.ui.BaseViewModel
import com.lutawav.architecturestudy.ui.ViewType
import com.lutawav.architecturestudy.util.addTo
import com.lutawav.architecturestudy.util.singleIoMainThread

class ImageViewModel(
    override val repository: NaverSearchRepositoryImpl
) : BaseViewModel<Image>(repository) {

    override val _data: MutableLiveData<List<Image>> = MutableLiveData()

    val data: LiveData<List<Image>>
        get() = _data

    val viewType = Transformations.map(data) { list ->
        if (list.isNotEmpty()) {
            ViewType.VIEW_SEARCH_RESULT
        } else {
            ViewType.VIEW_SEARCH_NO_RESULT
        }
    }

    override fun init() {
        repository.getLatestImageResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.value = it.keyword
                _data.value = it.images
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
                _data.value = imageRepo.images
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.value = message
            })
            .addTo(compositeDisposable)
    }
}