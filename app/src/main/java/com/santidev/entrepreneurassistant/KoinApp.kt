package com.santidev.entrepreneurassistant

import android.app.Application
import com.santidev.entrepreneurassistant.database.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApp: Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@KoinApp)
      modules(appModule)
    }
  }
}