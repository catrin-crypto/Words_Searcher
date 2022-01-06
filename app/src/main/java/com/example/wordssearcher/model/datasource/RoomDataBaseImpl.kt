package com.example.wordssearcher.model.datasource

import com.example.wordssearcher.model.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImpl  : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}