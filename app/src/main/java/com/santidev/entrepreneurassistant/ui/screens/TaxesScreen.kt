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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaxesScreen() {
  var montoBase by remember { mutableStateOf("") }
  var tipoImpuesto by remember { mutableStateOf("IVA 21%") }
  var expanded by remember { mutableStateOf(false) }
  var montoConImpuesto by remember { mutableStateOf(0.0) }
  var soloImpuesto by remember { mutableStateOf(0.0) }
  
  val tiposImpuesto = listOf(
    "IVA 21%" to 21.0,
    "IVA 10.5%" to 10.5,
    "Ingresos Brutos 3%" to 3.0,
    "Ganancias 35%" to 35.0
  )
  
  fun calcular() {
    val base = montoBase.toDoubleOrNull() ?: 0.0
    val porcentaje = tiposImpuesto.find { it.first == tipoImpuesto }?.second ?: 21.0
    
    if (base > 0) {
      soloImpuesto = base * porcentaje / 100
      montoConImpuesto = base + soloImpuesto
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
          text = "Calculadora de Impuestos",
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(bottom = 16.dp)
        )
        
        OutlinedTextField(
          value = montoBase,
          onValueChange = { montoBase = it },
          label = { Text("Monto base ($)") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        ExposedDropdownMenuBox(
          expanded = expanded,
          onExpandedChange = { expanded = !expanded }
        ) {
          OutlinedTextField(
            value = tipoImpuesto,
            onValueChange = { },
            readOnly = true,
            label = { Text("Tipo de impuesto") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
              .menuAnchor()
              .fillMaxWidth()
          )
          ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
          ) {
            tiposImpuesto.forEach { (nombre, _) ->
              DropdownMenuItem(
                text = { Text(nombre) },
                onClick = {
                  tipoImpuesto = nombre
                  expanded = false
                }
              )
            }
          }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
          onClick = { calcular() },
          modifier = Modifier.fillMaxWidth()
        ) {
          Text("Calcular")
        }
        
        if (montoConImpuesto > 0) {
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
              Text("Monto con impuesto: ${formatearPeso(montoConImpuesto)}")
              Text("Solo impuesto: ${formatearPeso(soloImpuesto)}")
            }
          }
        }
      }
    }
  }
}