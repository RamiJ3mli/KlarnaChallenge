package com.ramijemli.klarna

import android.app.Application

class KlarnaApplication : Application() {

    companion object {
        lateinit var instance: KlarnaApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}

