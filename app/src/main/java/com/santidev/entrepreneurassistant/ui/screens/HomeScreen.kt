package com.santidev.entrepreneurassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.santidev.entrepreneurassistant.CardViewModel
import com.santidev.entrepreneurassistant.utils.cards.AddCard
import com.santidev.entrepreneurassistant.utils.cards.ButtonAddCard
import com.santidev.entrepreneurassistant.utils.cards.CardList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
  val viewModel: CardViewModel = koinViewModel()
  var showAddCard by remember { mutableStateOf(false) }
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(8.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Mi almacén de productos",
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
      textAlign = TextAlign.Center,
    )
    
    Box(
      modifier = Modifier
        .fillMaxSize(),
      contentAlignment = Alignment.BottomEnd
    ) {
      CardList(
        viewModel = viewModel,
        onAddClick = { showAddCard = true }
      )
      
      // Mostrar la pantalla de agregar como una tarjeta flotante/modal
      if (showAddCard) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 1f))
            .clickable { showAddCard = false } // cerrar tocando afuera de la card
        )
        
        Card(
          modifier = Modifier
            .align(Alignment.Center)
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
          elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
          AddCard(
            viewModel = viewModel,
            onNavigateBack = { showAddCard = false },
            onSaveCard = { card ->
              viewModel.insertCard(card)
              showAddCard = false
            }
          )
        }
      }
      ButtonAddCard()
    }
  }
}