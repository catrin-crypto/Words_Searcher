package com.example.wordssearcher.model.datasource

import com.example.wordssearcher.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToBD(appState: AppState)
}