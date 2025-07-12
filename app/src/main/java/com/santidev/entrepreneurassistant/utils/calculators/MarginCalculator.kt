package com.santidev.entrepreneurassistant.utils.calculators

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MarginCalculator() {
  
  Column(modifier = Modifier
    .fillMaxWidth()
    .verticalScroll(rememberScrollState())
  ) {
    CalculateCost()
    Spacer(modifier = Modifier.height(50.dp))
    HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Gray)
    Spacer(modifier = Modifier.height(50.dp))
    CalculatePriceSale()
  }
}