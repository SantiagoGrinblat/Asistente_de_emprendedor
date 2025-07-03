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
import com.santidev.entrepreneurassistant.utils.formatearPeso
import kotlin.math.pow

@Composable
fun InflationScreen() {
  var precioOriginal by remember { mutableStateOf("") }
  var inflacionMensual by remember { mutableStateOf("") }
  var meses by remember { mutableStateOf("") }
  var precioAjustado by remember { mutableStateOf(0.0) }
  var aumento by remember { mutableStateOf(0.0) }
  
  fun calcular() {
    val original = precioOriginal.toDoubleOrNull() ?: 0.0
    val inflacion = inflacionMensual.toDoubleOrNull() ?: 0.0
    val mesesNum = meses.toDoubleOrNull() ?: 0.0
    
    if (original > 0 && inflacion > 0 && mesesNum > 0) {
      precioAjustado = original * (1 + inflacion / 100).pow(mesesNum)
      aumento = precioAjustado - original
    }
  }
  
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .verticalScroll(rememberScrollState())
  ) {
    Card(
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier.padding(16.dp)
      ) {
        Text(
          text = "Calculadora de Ajuste por Inflación",
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(bottom = 16.dp)
        )
        
        OutlinedTextField(
          value = precioOriginal,
          onValueChange = { precioOriginal = it },
          label = { Text("Precio original ($)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
          value = inflacionMensual,
          onValueChange = { inflacionMensual = it },
          label = { Text("Inflación mensual (%)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
          value = meses,
          onValueChange = { meses = it },
          label = { Text("Cantidad de meses") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
          onClick = { calcular() },
          modifier = Modifier.fillMaxWidth()
        ) {
          Text("Calcular")
        }
        
        if (precioAjustado > 0) {
          Spacer(modifier = Modifier.height(16.dp))
          Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
          ) {
            Column(
              modifier = Modifier.padding(16.dp)
            ) {
              Text(
                text = "Resultados:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
              )
              Text("Precio ajustado: ${formatearPeso(precioAjustado)}")
              Text("Aumento total: ${formatearPeso(aumento)}")
              Text(
                "Aumento %: ${
                  String.format(
                    "%.1f",
                    (aumento / precioOriginal.toDouble() * 100)
                  )
                }%"
              )
            }
          }
        }
      }
    }
  }
}