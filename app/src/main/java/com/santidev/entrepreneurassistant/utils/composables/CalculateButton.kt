package com.santidev.entrepreneurassistant.utils.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculateButton(onClick: () -> Unit) {
  Button(
    onClick = onClick,
    modifier = Modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.surface),
    shape = RoundedCornerShape(10.dp),
    elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp)
  ) {
    Text("Calcular", color = Color.White, fontSize = 18.sp)
  }
}