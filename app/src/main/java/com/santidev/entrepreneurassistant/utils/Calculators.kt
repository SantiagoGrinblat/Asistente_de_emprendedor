package com.santidev.entrepreneurassistant.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.entrepreneurassistant.ui.screens.BalanceScreen
import com.santidev.entrepreneurassistant.ui.screens.InflationScreen
import com.santidev.entrepreneurassistant.ui.screens.MarginScreen
import com.santidev.entrepreneurassistant.ui.screens.TaxesScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun Calculators() {
  var tabSelecction by remember { mutableStateOf(0) }
  
  val tabs = listOf("Margin", "Balance", "Inflation", "Taxes")
  
  Column(modifier = Modifier
    .fillMaxSize()
    .padding(16.dp)
  ) {
    Text(
      text = "Calculadoras para Emprendedores",
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
      textAlign = TextAlign.Center
    )
    
    TabRow(selectedTabIndex = tabSelecction) {
      tabs.forEachIndexed { index, titulo ->
        Tab(
          selected = tabSelecction == index,
          onClick = { tabSelecction = index },
          text = { Text(titulo) }
        )
      }
    }
    Spacer(modifier = Modifier.height(16.dp))
    
    when(tabSelecction) {
      0 -> MarginScreen()
      1 -> BalanceScreen()
      2 -> InflationScreen()
      3 -> TaxesScreen()
    }
  }
}