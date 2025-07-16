package com.santidev.entrepreneurassistant.utils

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.santidev.entrepreneurassistant.database.CardEntity

@Composable
fun CardItem(
  card: CardEntity,
  onDeleteClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(4.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    )
  ) {
    Column(modifier = Modifier.padding(8.dp)) {
      
      AsyncImage(
        model = Uri.parse(card.imageUri),
        contentDescription = card.title,
        modifier = Modifier
          .fillMaxWidth()
          .height(500.dp)
          .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
      )
      
      Spacer(modifier = Modifier.height(8.dp))
      
      Text(
        text = card.title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold
      )
      
      Spacer(modifier = Modifier.height(4.dp))
      
      Text(
        text = card.description,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Precio: ${card.price}",
          style = MaterialTheme.typography.bodyLarge,
          fontWeight = FontWeight.Medium
        )
        
        IconButton(onClick = onDeleteClick) {
          Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Eliminar",
            tint = Color(0xFF019863)
          )
        }
      }
      Spacer(modifier = Modifier.height(6.dp))
    }
  }
}