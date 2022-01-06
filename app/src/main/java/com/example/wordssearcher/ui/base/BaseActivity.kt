package com.example.wordssearcher.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.example.wordssearcher.model.data.AppState
import com.example.wordssearcher.viewmodel.BaseViewModel
import com.example.wordssearcher.viewmodel.Interactor

abstract class BaseActivity<T : AppState, I : Interactor<T> > : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(dataModel: T)

}