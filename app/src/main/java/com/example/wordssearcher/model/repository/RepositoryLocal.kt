package com.example.wordssearcher.model.repository

import com.example.wordssearcher.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}