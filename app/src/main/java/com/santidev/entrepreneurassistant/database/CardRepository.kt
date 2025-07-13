package com.santidev.entrepreneurassistant.database

import kotlinx.coroutines.flow.Flow

class CardRepository(private val cardDao: CardDao) {
  fun getAllCards(): Flow<List<CardEntity>> = cardDao.getAllCards()
  
  suspend fun insertCard(card: CardEntity) = cardDao.insertCard(card)
  
  suspend fun deleteCard(card: CardEntity) = cardDao.deleteCard(card)
  
  suspend fun updateCard(card: CardEntity) = cardDao.updateCard(card)
  
  suspend fun getCardById(id: Long): CardEntity? = cardDao.getCardById(id)
}