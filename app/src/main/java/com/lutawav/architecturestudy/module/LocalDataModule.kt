package com.lutawav.architecturestudy.module

import android.content.Context
import androidx.room.Room
import com.lutawav.architecturestudy.data.database.SearchHistoryDatabase
import com.lutawav.architecturestudy.data.source.local.NaverSearchLocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {

    single {
        Room.databaseBuilder(
            get(),
            SearchHistoryDatabase::class.java, "search_history.db"
        )
            .build()
    }

    single {
        androidContext().getSharedPreferences(
            NaverSearchLocalDataSource.PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }
}