package com.example.wordssearcher.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.example.wordssearcher.model.data.AppState
import com.example.wordssearcher.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

}