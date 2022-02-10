package com.lutawav.architecturestudy.module

import com.lutawav.architecturestudy.ui.blog.BlogViewModel
import com.lutawav.architecturestudy.ui.image.ImageViewModel
import com.lutawav.architecturestudy.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { MovieViewModel(get()) }
    viewModel { ImageViewModel(get()) }
    viewModel { BlogViewModel(get()) }
}