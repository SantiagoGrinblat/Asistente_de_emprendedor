package com.santidev.entrepreneurassistant.utils.calculators

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.entrepreneurassistant.utils.composables.Reusable.CalculateButton
import com.santidev.entrepreneurassistant.utils.composables.Reusable.OptimizedTextField
import com.santidev.entrepreneurassistant.utils.composables.calculatesFunctions.calcularInflacion

@Composable
fun InflationCalculator() {
  var originalPrice by remember { mutableStateOf("") }
  var monthlyInflation by remember { mutableStateOf("") }
  var months by remember { mutableStateOf("") }
  var showResults by remember { mutableStateOf(false) }
  
  
  val results: Triple<Double, Double, String> by remember(originalPrice, monthlyInflation, months) {
    derivedStateOf {
      calcularInflacion(
        originalPrice.toDoubleOrNull() ?: 0.0,
        monthlyInflation.toDoubleOrNull() ?: 0.0,
        months.toDoubleOrNull() ?: 0.0
      )
    }
  }
  
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp)
  ) {
    Card(
      modifier = Modifier.fillMaxWidth(),
      colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Ajuste por Inflación",
          fontSize = 21.sp,
          fontWeight = FontWeight.Bold,
          modifier = Modifier
            .padding(bottom = 16.dp)
            .align(Alignment.CenterHorizontally)
        )
        
        Text(
          text = "* Calcula el valor agreado al producto por inflacion *",
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.Center,
          fontSize = 16.sp
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        OptimizedTextField(
          value = originalPrice,
          onValueChange = { originalPrice = it },
          label = "Precio original ($)"
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OptimizedTextField(
          value = monthlyInflation,
          onValueChange = { monthlyInflation = it },
          label = "Inflación mensual (%)"
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OptimizedTextField(
          value = months,
          onValueChange = { months = it },
          label = "Meses con esa tasa de inflación"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        CalculateButton(onClick = { showResults = true })
        
        if (showResults && results.first > 0) {
          Spacer(modifier = Modifier.height(8.dp))
          HorizontalDivider(thickness = 1.dp, color = Color.Gray)
          Spacer(modifier = Modifier.height(8.dp))
          
          Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Text(
                text = "Resultados:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
              )
              Text("Precio ajustado: ${formatearPeso(results.first)}", fontWeight = FontWeight.Bold)
              Text("Aumento total: ${formatearPeso(results.second)}", fontWeight = FontWeight.Bold)
              Text("Aumento %: ${results.third}%", fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }
}