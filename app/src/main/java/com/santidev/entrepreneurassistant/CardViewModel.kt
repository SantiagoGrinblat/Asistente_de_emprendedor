package com.santidev.entrepreneurassistant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santidev.entrepreneurassistant.database.CardEntity
import com.santidev.entrepreneurassistant.database.CardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CardViewModel(private val repository: CardRepository): ViewModel() {
  val allCards: Flow<List<CardEntity>> = repository.getAllCards()
  
  //funcion para simplificar el scope
  fun ViewModel.launchIO(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
      block()
    }
  }
  
  fun insertCard(card: CardEntity) {
    launchIO {
      repository.insertCard(card)
    }
  }
  
  fun deleteCard(card: CardEntity) {
    launchIO {
      repository.deleteCard(card)
    }
  }
  
  fun updateCard(card: CardEntity) {
    launchIO {
      repository.updateCard(card)
    }
  }
}