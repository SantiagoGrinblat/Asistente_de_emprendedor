package com.santidev.entrepreneurassistant.utils.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PriceEditor(
  price: Int,
  onPriceChange: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    
    FloatingActionButton(
      onClick = {
        if (price > 0) {
          onPriceChange(price - 1)
        }
      },
      modifier = Modifier.size(35.dp),
      containerColor = Color.White,
    ) {
      Icon(
        Icons.Default.Remove,
        contentDescription = "Disminuir cantidad",
        modifier = Modifier.size(25.dp),
        tint = Color(0xFF029863)
      )
    }
    
    Spacer(modifier = Modifier.width(10.dp))
    
    Text(
      text = "$price",
      style = MaterialTheme.typography.titleLarge,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    
    Spacer(modifier = Modifier.width(10.dp))
    
    FloatingActionButton(
      onClick = {
        onPriceChange(price + 1)
      },
      modifier = Modifier.size(35.dp),
      containerColor = Color.White,
    ) {
      Icon(
        Icons.Default.Add,
        contentDescription = "Aumentar cantidad",
        modifier = Modifier.size(25.dp),
        tint = Color(0xFF029863)
      )
    }
  }
}
