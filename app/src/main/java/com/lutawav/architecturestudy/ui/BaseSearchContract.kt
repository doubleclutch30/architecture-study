package com.lutawav.architecturestudy.ui

interface BaseSearchContract {

    interface View<T, H> : BaseContract.View {
        val presenter: T

        fun updateUi(keyword: String, data: List<H>)

        fun showEmptyResultView()
        fun showResultListView()

        fun hideEmptyResultView()
        fun hideResultListView()

        fun updateResult(result: List<H>)
    }

    interface Presenter : BaseContract.Presenter {
        fun search(keyword: String)

        fun updateSearchHistory(func: () -> Unit)

        fun clearSearchHistory(func: () -> Unit)
    }
}