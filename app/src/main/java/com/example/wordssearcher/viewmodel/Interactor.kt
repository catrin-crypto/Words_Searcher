package com.example.wordssearcher.viewmodel

//import io.reactivex.Observable

interface Interactor <T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}