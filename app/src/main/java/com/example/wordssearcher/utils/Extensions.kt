package com.example.wordssearcher.utils

import com.example.wordssearcher.model.data.AppState
import com.example.wordssearcher.model.data.DataModel
import com.example.wordssearcher.model.data.room.HistoryEntity

fun String.Companion.getEmptyString(): String = ""

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!)
            }
        }
        else -> null
    }
}

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val dataModel = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            dataModel.add(DataModel(entity.word, null))
        }
    }
    return dataModel
}