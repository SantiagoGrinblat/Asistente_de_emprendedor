package com.santidev.entrepreneurassistant.database

import androidx.room.Room
import com.santidev.entrepreneurassistant.CardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  single {
    Room.databaseBuilder(
      androidContext(),
      AppDatabase::class.java,
      "card_database"
    ).build()
  }
  
  // DAO
  single { get<AppDatabase>().cardDao() }
  
  // Repository
  single { CardRepository(get()) }
  
  // ViewModel
  viewModel { CardViewModel(get()) }
}