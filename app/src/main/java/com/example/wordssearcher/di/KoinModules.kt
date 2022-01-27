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
import com.example.wordssearcher.ui.history.HistoryActivity
import com.example.wordssearcher.ui.history.HistoryInteractor
import com.example.wordssearcher.ui.history.HistoryViewModel
import com.example.wordssearcher.ui.main.MainActivity
import com.example.wordssearcher.ui.main.MainActivityViewModel
import com.example.wordssearcher.ui.main.MainInteractor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val application = module {
    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }

    single{Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build()}
    single{get<HistoryDatabase>().historyDao()}
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplLocal(RoomDataBaseImpl(get())) }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped {MainInteractor(get(),get())  }
        viewModel{MainActivityViewModel(get())}
    }
//    factory { MainInteractor(get(), get()) }
//    factory { MainActivityViewModel(get()) }

}
val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped{HistoryInteractor(get(),get())}
        viewModel{HistoryViewModel(get())}
    }
//    factory { HistoryViewModel(get()) }
//    factory { HistoryInteractor(get(), get()) }
}