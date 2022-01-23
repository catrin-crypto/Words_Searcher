package com.example.wordssearcher.ui.main

import androidx.lifecycle.LiveData
import com.example.model.data.AppState
import com.example.wordssearcher.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {

    private var job: Job? = null
    private val liveDataForViewToObserve: LiveData<AppState> = mutableLiveData
    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        mutableLiveData.value = AppState.Loading(null)
        cancelJob()
       job = viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            mutableLiveData.postValue(interactor.getData(word, isOnline))
        }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}