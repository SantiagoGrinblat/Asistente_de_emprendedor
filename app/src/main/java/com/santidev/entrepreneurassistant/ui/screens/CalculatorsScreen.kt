package com.santidev.entrepreneurassistant.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.santidev.entrepreneurassistant.utils.calculators.InflationCalculator
import com.santidev.entrepreneurassistant.utils.calculators.MarginCalculator
import com.santidev.entrepreneurassistant.utils.calculators.TaxesCalculator

@Composable
fun CalculatorsScreen(navController: NavHostController) {
  var tabSelecction by remember { mutableStateOf(0) }
  
  val tabs = listOf("Márgenes", "Inflación", "Impuestos")
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    Text(
      text = "Calculadoras para emprendedores",
      fontSize = 24.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
      textAlign = TextAlign.Center,
    )
    
    TabRow(selectedTabIndex = tabSelecction) {
      tabs.forEachIndexed { index, titulo ->
        Tab(
          selected = tabSelecction == index,
          onClick = { tabSelecction = index },
          text = { Text(titulo) },
          modifier = Modifier.padding(bottom = 16.dp),
        )
      }
    }
    Spacer(modifier = Modifier.height(16.dp))
    
    when (tabSelecction) {
      0 -> MarginCalculator()
      1 -> InflationCalculator()
      2 -> TaxesCalculator()
    }
  }
}