package com.example.wordssearcher.di

import androidx.room.Room
import com.example.model.data.DataModel
import com.example.wordssearcher.model.room.HistoryDatabase
import com.example.wordssearcher.model.repository.RepositoryImplLocal
import com.example.wordssearcher.model.repository.RepositoryLocal
import com.example.wordssearcher.model.datasource.RetrofitImpl
import com.example.wordssearcher.model.datasource.RoomDataBaseImpl
import com.example.wordssearcher.model.repository.Repository
import com.example.wordssearcher.model.repository.RepositoryImpl
import com.example.wordssearcher.ui.history.HistoryInteractor
import com.example.wordssearcher.ui.history.HistoryViewModel
import com.example.wordssearcher.ui.main.MainActivityViewModel
import com.example.wordssearcher.ui.main.MainInteractor
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }

    single{Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build()}
    single{get<HistoryDatabase>().historyDao()}
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplLocal(RoomDataBaseImpl(get())) }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainActivityViewModel(get()) }

}
val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}