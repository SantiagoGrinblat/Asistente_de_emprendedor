package com.santidev.entrepreneurassistant.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
  @Query("SELECT * FROM cards")
  fun getAllCards(): Flow<List<CardEntity>>
  
  @Insert
  suspend fun insertCard(card: CardEntity)
  
  @Delete
  suspend fun deleteCard(card: CardEntity)
  
  @Update
  suspend fun updateCard(card: CardEntity)
  
  @Query("SELECT * FROM cards WHERE id = :id")
  suspend fun getCardById(id: Long): CardEntity?
  
  @Query( "UPDATE cards SET price = :newPrice WHERE id = :cardId")
  suspend fun updatePrice(cardId: Long, newPrice: Int)
}