package com.santidev.entrepreneurassistant.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity (
  @PrimaryKey(autoGenerate = true)
  val id: Long? = null,
  val imageUri: String,
  val title: String,
  val description: String,
  val price: Int,
)