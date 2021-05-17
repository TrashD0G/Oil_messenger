package com.example.oilmessenger.di

import android.app.Application

class OilMessengerApplication : Application() {

    lateinit var applicationAppComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationAppComponent = DaggerAppComponent.create()
    }
}