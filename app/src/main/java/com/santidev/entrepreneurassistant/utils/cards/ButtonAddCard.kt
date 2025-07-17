package com.santidev.entrepreneurassistant.utils.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.santidev.entrepreneurassistant.CardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ButtonAddCard(
  viewModel: CardViewModel = koinViewModel(),
  onNavigateBack: () -> Unit = {}
) {
  var dialogOpen by remember { mutableStateOf(false) }
  
  if (dialogOpen) {
    Dialog(onDismissRequest = { dialogOpen = false }) {
      Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 12.dp,
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        AddCard(
          viewModel = viewModel,
          onNavigateBack = {
            dialogOpen = false
            onNavigateBack()
          }
        )
      }
    }
  }
  FloatingActionButton(
    onClick = { dialogOpen = true },
    containerColor = Color(0xFF019863)
  ) {
    Icon(
      imageVector = Icons.Default.Add,
      contentDescription = "Icon Add",
      tint = Color.White
    )
  }
}