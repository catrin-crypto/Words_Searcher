package com.example.wordssearcher.model.datasource

import com.example.wordssearcher.model.data.DataModel
import io.reactivex.Observable

class DataSourceLocal (private val remoteProvider: RoomDataBaseImpl = RoomDataBaseImpl()) :
DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}