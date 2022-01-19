package com.example.wordssearcher.model.repository

import com.example.wordssearcher.model.data.DataModel
import com.example.wordssearcher.model.datasource.DataSource

class RepositoryImpl (private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}