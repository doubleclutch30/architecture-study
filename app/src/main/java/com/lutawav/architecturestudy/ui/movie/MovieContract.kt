package com.lutawav.architecturestudy.ui.movie

import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.ui.BaseSearchContract

interface MovieContract {

    interface View: BaseSearchContract.View<Presenter, Movie>

    interface Presenter: BaseSearchContract.Presenter
}