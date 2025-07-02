package com.santidev.entrepreneurassistant.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun CalculatorContainer() {
  var tabSelected by remember { mutableStateOf(0) }
  
  when (tabSelected) {
    0 -> ProfitCalculator()
    1 -> EquilibriumPointCalculator()
    2 -> InflationCalculator()
    3 -> TaxesCalculator()
  }
}