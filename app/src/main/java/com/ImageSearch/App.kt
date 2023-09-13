package com.ImageSearch

import android.app.Application
import timber.log.Timber

class App: Application() {
    override fun onCreate() {
        super.onCreate()


        Timber.plant(Timber.DebugTree()) // 디버그 빌드에서만 로그를 출력하도록 설정

    }
}