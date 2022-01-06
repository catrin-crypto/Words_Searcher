package com.example.wordssearcher.application

import android.app.Application
import com.example.wordssearcher.di.application
import com.example.wordssearcher.di.mainScreen
import org.koin.core.context.startKoin

class WordsSearcherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}