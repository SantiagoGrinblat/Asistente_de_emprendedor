package com.santidev.entrepreneurassistant.utils.calculators

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculateCost() {
  var cost by remember { mutableStateOf("") }
  var marginDesired by remember { mutableStateOf("") }
  var priceSale by remember { mutableStateOf(0.0) }
  var revenue by remember { mutableStateOf(0.0) }
  
  fun calculateCost() {
    val costNum = cost.toDoubleOrNull() ?: 0.0
    val marginNum = marginDesired.toDoubleOrNull() ?: 0.0
    
    if (costNum > 0 && marginNum > 0) {
      priceSale = costNum * (1 + marginNum / 100)
      revenue = priceSale - costNum
    }
  }
  
  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = "Calculadora de Precios con Margen sobre el costo",
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 8.dp)
      )
      Text(
        text = "*Ideal para emprendedores, mayoristas, fabricantes, etc*",
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Center,
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
        onClick = { calculateCost() },
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(10),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp)
      ) {
        Text("Calcular")
      }
      
      if (priceSale > 0) {
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Card(
          colors = CardDefaults.cardColors(containerColor = Color.Transparent),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Text(
              text = "Resultados:",
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(bottom = 8.dp)
            )
            Text("Precio de venta: ${formatearPeso(priceSale)}", fontWeight = FontWeight.Bold)
            Text("Ganancia: ${formatearPeso(revenue)}", fontWeight = FontWeight.Bold)
          }
        }
      }
    }
  }
}