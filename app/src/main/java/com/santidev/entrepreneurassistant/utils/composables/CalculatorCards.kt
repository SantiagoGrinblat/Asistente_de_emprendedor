package com.santidev.entrepreneurassistant.utils.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
private fun CalculatorCards(
  title: String,
  subTitle: String,
  input1Value: String,
  input2Value: String,
  input1Label: String,
  input2Label: String,
  onInput1Change: (String) -> Unit,
  onInput2change: (String) -> Unit,
  onCalculate: () -> Unit,
  showResults: Boolean,
  result1Label: String,
  result1Value: Double,
  result2Label: String,
  result2Value: Double

) {
  Card(modifier = Modifier
    .fillMaxWidth(),
    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = title,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
      )
      Text(
        text = subTitle,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
      )
      OptimizedTextField(
        value = input1Value,
        onValueChange = onInput1Change,
        label = input1Label
      )
      
      Spacer(modifier = Modifier.height(8.dp))
      
      OptimizedTextField(
        value = input2Value,
        onValueChange = onInput2change,
        label = input2Label
      )
      
      Spacer(modifier = Modifier.height(16.dp))
      
      CalculateButton(onClick = onCalculate)
      
      if (showResults) {
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        ResultsCard(
          result1Label = result1Label,
          result1Value = result1Value,
          result2Label = result2Label,
          result2Value = result2Value
        )
      }
      
    }
  }
}