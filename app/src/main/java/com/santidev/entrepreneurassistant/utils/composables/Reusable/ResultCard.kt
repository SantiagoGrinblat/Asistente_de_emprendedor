package com.santidev.entrepreneurassistant.utils.composables.Reusable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.entrepreneurassistant.utils.calculators.formatearPeso

@Composable
fun ResultsCard(
  result1Label: String,
  result1Value: Double,
  result2Label: String,
  result2Value: Double
) {
  Card(
    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = "Resultados:",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
      )
      Text("$result1Label: ${formatearPeso(result1Value)}", fontWeight = FontWeight.Bold)
      Text("$result2Label: ${formatearPeso(result2Value)}", fontWeight = FontWeight.Bold)
    }
  }
}