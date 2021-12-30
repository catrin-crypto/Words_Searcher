package com.example.wordssearcher.model.datasource

import com.example.wordssearcher.model.data.DataModel
import io.reactivex.Observable

class DataSourceRemote (private val remoteProvider: RetrofitImpl = RetrofitImpl()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}