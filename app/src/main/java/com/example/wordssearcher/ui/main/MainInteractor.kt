package com.example.wordssearcher.ui.main

import com.example.wordssearcher.model.data.AppState
import com.example.wordssearcher.model.data.DataModel
import com.example.wordssearcher.model.repository.Repository
import com.example.wordssearcher.model.repository.RepositoryLocal
import com.example.wordssearcher.viewmodel.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState


        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appState)


        } else {
            appState = AppState.Success(localRepository.getData(word))

        }
        return appState
    }
}