package com.example.wordssearcher.ui.main

import com.example.wordssearcher.model.data.AppState
import com.example.wordssearcher.model.data.DataModel
import com.example.wordssearcher.model.repository.Repository
import com.example.wordssearcher.viewmodel.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
        if (fromRemoteSource) {
            remoteRepository
        } else {
            localRepository
        }.getData(word)
        )
    }
}