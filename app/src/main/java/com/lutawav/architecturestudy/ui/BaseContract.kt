package com.lutawav.architecturestudy.ui

interface BaseContract {

    interface View {
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun handleError(e: Throwable)
    }
}