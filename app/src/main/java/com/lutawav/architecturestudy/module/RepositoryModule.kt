package com.lutawav.architecturestudy.module

import com.lutawav.architecturestudy.data.repository.NaverSearchRepository
import com.lutawav.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.lutawav.architecturestudy.data.source.local.NaverSearchLocalDataSource
import com.lutawav.architecturestudy.data.source.local.NaverSearchLocalDataSourceImpl
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NaverSearchLocalDataSource> { NaverSearchLocalDataSourceImpl(get(), get()) }
    single<NaverSearchRemoteDataSource> { NaverSearchRemoteDataSourceImpl(get()) }
    single<NaverSearchRepository> { NaverSearchRepositoryImpl(get(), get()) }
}
