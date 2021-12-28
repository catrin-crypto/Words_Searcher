package com.example.wordssearcher.ui.base

import com.example.wordssearcher.model.data.AppState

interface View {

    fun renderData(appState: AppState)

}