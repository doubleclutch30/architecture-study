package com.lutawav.architecturestudy.ui.image

import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.ui.BaseContract
import com.lutawav.architecturestudy.ui.BaseSearchContract

interface ImageContract : BaseContract {

    interface View : BaseSearchContract.View<Presenter, Image>

    interface Presenter : BaseSearchContract.Presenter

}