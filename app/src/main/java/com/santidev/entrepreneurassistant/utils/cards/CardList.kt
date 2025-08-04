package com.santidev.entrepreneurassistant.utils.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.santidev.entrepreneurassistant.CardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardList(
  viewModel: CardViewModel = koinViewModel(),
  onAddClick: () -> Unit
) {
  val cards by viewModel.allCards.collectAsState(initial = emptyList())
  
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(cards) { card ->
      CardItem(
        card = card,
        onDeleteClick = { viewModel.deleteCard(card) },
        onPriceUpdate = { cardToUpdate, newPrice ->
          viewModel.updateCard(cardToUpdate, newPrice)
        }
      )
    }
  }
}