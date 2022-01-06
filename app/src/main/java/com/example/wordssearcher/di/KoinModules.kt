package com.example.wordssearcher.di

import com.example.wordssearcher.model.data.DataModel
import com.example.wordssearcher.model.datasource.RetrofitImpl
import com.example.wordssearcher.model.datasource.RoomDataBaseImpl
import com.example.wordssearcher.model.repository.Repository
import com.example.wordssearcher.model.repository.RepositoryImpl
import com.example.wordssearcher.ui.main.MainActivityViewModel
import com.example.wordssearcher.ui.main.MainInteractor
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImpl(RetrofitImpl()) }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImpl(RoomDataBaseImpl()) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainActivityViewModel(get()) }
}