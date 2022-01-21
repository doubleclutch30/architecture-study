package com.lutawav.architecturestudy.ui.blog

import com.lutawav.architecturestudy.data.model.Blog
import com.lutawav.architecturestudy.ui.BaseContract
import com.lutawav.architecturestudy.ui.BaseSearchContract

interface BlogContract : BaseContract {

    interface View : BaseSearchContract.View<Presenter, Blog>

    interface Presenter : BaseSearchContract.Presenter
}