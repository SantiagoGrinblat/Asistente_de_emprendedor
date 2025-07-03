package com.santidev.entrepreneurassistant.ui.screens

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
import androidx.navigation.NavHostController

@Composable
fun BalanceScreen() {
  var costFixed by remember { mutableStateOf("") }
  var costVariable by remember { mutableStateOf("") }
  var priceUnit by remember { mutableStateOf("") }
  var balancePoint by remember { mutableStateOf(0.0) }
  var salesNeeded by remember { mutableStateOf(0.0) }
  
  fun calculateBalance() {
    val fixed = costFixed.toDoubleOrNull() ?: 0.0
    val variables = costVariable.toDoubleOrNull() ?: 0.0
    val price = priceUnit.toDoubleOrNull() ?: 0.0
    
    if (fixed > 0 && price > variables) {
      balancePoint = fixed / (price - variables)
      salesNeeded = balancePoint * price
    }
  }
  
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .verticalScroll(rememberScrollState())
  ) {
    Card(modifier = Modifier.fillMaxWidth()) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Calculadora de Punto de Equilibrio",
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
          value = costFixed,
          onValueChange = { costFixed = it },
          label = { Text("Costos fijos mensuales ($)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
          value = costVariable,
          onValueChange = { costVariable = it },
          label = { Text("Costo variable por unidad ($)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
          value = priceUnit,
          onValueChange = { priceUnit = it },
          label = { Text("Precio de venta por unidad ($)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
          onClick = { calculateBalance() },
          modifier = Modifier.fillMaxWidth()
        ) {
          Text("Calcular")
        }
        if (balancePoint > 0) {
          Spacer(modifier = Modifier.height(16.dp))
          Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
            Column(modifier = Modifier.padding(16.dp)) {
              Text(
                text = "Resultados:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
              )
              Text(text = "Unidades a vender: ${String.format("%.0f", balancePoint)} por mes")
              Text(text = "Ventas necesarias: ${String.format("%.0f", salesNeeded)} por mes")
              Text(text = "Unidades por día: ${String.format("%.1f", balancePoint / 30)}")
            }
          }
        }
      }
    }
  }
}