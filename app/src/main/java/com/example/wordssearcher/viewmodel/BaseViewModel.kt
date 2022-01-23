package com.example.wordssearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.data.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel <T : AppState>(
    protected val mutableLiveData: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        viewModelCoroutineScope.cancel()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
    abstract fun getData(word: String, isOnline: Boolean)
    abstract fun handleError(error: Throwable)
}