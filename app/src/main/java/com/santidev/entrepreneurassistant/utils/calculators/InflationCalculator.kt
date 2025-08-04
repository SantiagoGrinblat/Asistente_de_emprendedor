package com.santidev.entrepreneurassistant.utils.calculators

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow

@Composable
fun InflationCalculator() {
  var originalPrice by remember { mutableStateOf("") }
  var monthlyInflation by remember { mutableStateOf("") }
  var months by remember { mutableStateOf("") }
  var adjustedPrice by remember { mutableStateOf(0.0) }
  var increase by remember { mutableStateOf(0.0) }
  
  fun calcular() {
    val original = originalPrice.toDoubleOrNull() ?: 0.0
    val inflation = monthlyInflation.toDoubleOrNull() ?: 0.0
    val monthsNum = months.toDoubleOrNull() ?: 0.0
    
    if (original > 0 && inflation > 0 && monthsNum > 0) {
      adjustedPrice = original * (1 + inflation / 100).pow(monthsNum)
      increase = adjustedPrice - original
    }
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .verticalScroll(rememberScrollState())
  ) {
    Card(
      modifier = Modifier.fillMaxWidth(),
      colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
      Column(
        modifier = Modifier.padding(16.dp)
      ) {
        Text(
          text = "Ajuste por Inflación",
          fontSize = 21.sp,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
        )
        
        OutlinedTextField(
          value = originalPrice,
          onValueChange = { originalPrice = it },
          label = { Text("Precio original ($)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
          value = monthlyInflation,
          onValueChange = { monthlyInflation = it },
          label = { Text("Inflación mensual (%)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
          value = months,
          onValueChange = { months = it },
          label = { Text("Meses con esa tasa de inflación") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
          onClick = { calcular() },
          modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
          shape = RoundedCornerShape(10),
          elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp)
        ) {
          Text("Calcular", color = Color.White, fontSize = 18.sp)
        }
        
        if (adjustedPrice > 0) {
          Spacer(modifier = Modifier.height(8.dp))
          HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Gray)
          Spacer(modifier = Modifier.height(8.dp))
          Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(
              modifier = Modifier.padding(16.dp)
            ) {
              Text(
                text = "Resultados:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
              )
              Text("Precio ajustado: ${formatearPeso(adjustedPrice)}", fontWeight = FontWeight.Bold)
              Text("Aumento total: ${formatearPeso(increase)}", fontWeight = FontWeight.Bold)
              Text("Aumento %: ${String.format("%.1f", (increase / originalPrice.toDouble() * 100) )}%", fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }
}