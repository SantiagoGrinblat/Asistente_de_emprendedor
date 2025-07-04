package com.santidev.entrepreneurassistant.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MarginCalculator() {
  var cost by remember { mutableStateOf("") }
  var marginDesired by remember { mutableStateOf("") }
  var priceSale by remember { mutableStateOf(0.0) }
  var revenue by remember { mutableStateOf(0.0) }
  
  fun calculate() {
    val costNum = cost.toDoubleOrNull() ?: 0.0
    val marginNum = marginDesired.toDoubleOrNull() ?: 0.0
    
    if (costNum > 0 && marginNum > 0) {
      priceSale = costNum / (1 - marginNum / 100)
      revenue = priceSale - costNum
    }
  }
  
  Column(modifier = Modifier
    .fillMaxWidth()
    .verticalScroll(rememberScrollState())
  ) {
    Card(modifier = Modifier.fillMaxWidth()) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Calculadora de Precio con Margen de ganancias",
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
          value = cost,
          onValueChange = { cost = it },
          label = { Text("Costo del producto ($)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
          value = marginDesired,
          onValueChange = { marginDesired = it },
          label = { Text("Margen deseado (%)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
          onClick = { calculate() },
          modifier = Modifier.fillMaxWidth()
        ) {
          Text("Calcular")
        }
        
        if (priceSale > 0) {
          Spacer(modifier = Modifier.height(16.dp))
          Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
            Column(modifier = Modifier.padding(16.dp)) {
              Text(
                text = "Resultados:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
              )
              Text("Precio de venta: ${formatearPeso(priceSale)}")
              Text("Ganancia: ${formatearPeso(revenue)}")
              Text("Ganancia %: ${String.format("%.1f", (revenue / priceSale * 100))}%")
            }
          }
        }
      }
    }
  }
}