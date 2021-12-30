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

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}