package com.example.wordssearcher.model.repository

import com.example.wordssearcher.model.data.AppState
import com.example.wordssearcher.model.data.DataModel
import com.example.wordssearcher.model.datasource.DataSourceLocal

class RepositoryImplLocal(private val dataSourceLocal: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) {
        dataSourceLocal.saveToBD(appState)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSourceLocal.getData(word)
    }

}