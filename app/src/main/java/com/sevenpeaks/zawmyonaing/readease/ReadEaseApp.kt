package com.sevenpeaks.zawmyonaing.readease

import android.app.Application
import com.sevenpeaks.zawmyonaing.readease.analytics.AnalyticsManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ReadEaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        AnalyticsManager.init(context = applicationContext)
    }
}