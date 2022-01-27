package com.example.wordssearcher.model.datasource

import com.example.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToBD(appState: AppState)
}