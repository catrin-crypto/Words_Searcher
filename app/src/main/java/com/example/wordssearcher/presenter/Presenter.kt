package com.example.wordssearcher.presenter

import com.example.wordssearcher.model.data.AppState
import com.example.wordssearcher.ui.base.View

interface Presenter<T : AppState, V : View> {
    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}