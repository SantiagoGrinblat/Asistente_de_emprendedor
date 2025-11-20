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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.entrepreneurassistant.utils.composables.Reusable.CalculateButton
import com.santidev.entrepreneurassistant.utils.composables.Reusable.OptimizedTextField
import com.santidev.entrepreneurassistant.utils.composables.calculatesFunctions.calcularImpuestos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaxesCalculator() {
  var montoBase by remember { mutableStateOf("") }
  var tipoImpuesto by remember { mutableStateOf("IVA 21%") }
  var expanded by remember { mutableStateOf(false) }
  var impuestoPersonalizado by remember { mutableStateOf("") }
  var showResults by remember { mutableStateOf(false) }
  
  // Nuevo estado para el impuesto personalizado para recordar el valor que seleccione el usuario
  val mostrarImpuestoPersonalizado = tipoImpuesto == "Personalizado"
  
  val tiposImpuesto = listOf(
    "IVA 21%" to 21.0,
    "IVA 10.5%" to 10.5,
    "Ingresos Brutos 3%" to 3.0,
    "Ganancias 35%" to 35.0,
    "Personalizado" to 0.0 // Nueva opcion para el impuesto personalizado
  )
  
  val results: Pair<Double, Double> by remember(montoBase, tipoImpuesto, impuestoPersonalizado) {
    derivedStateOf {
      val porcentaje = if (tipoImpuesto == "Personalizado") {
        impuestoPersonalizado.toDoubleOrNull() ?: 0.0
      } else {
        tiposImpuesto.find { it.first == tipoImpuesto }?.second ?: 21.0
      }
      calcularImpuestos(montoBase.toDoubleOrNull() ?: 0.0, porcentaje)
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
          text = "Calculadora de Impuestos",
          fontSize = 21.sp,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier
            .padding(bottom = 16.dp)
            .align(Alignment.CenterHorizontally)
        )
        
        Text(
          text = "* Calcula el impuesto agregado sobre un monto base *",
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.Center,
          fontSize = 16.sp
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        OptimizedTextField(
          value = montoBase,
          onValueChange = { montoBase = it },
          label = "Monto base ($)"
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
                text = {
                  Text(
                    nombre,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                  )
                },
                onClick = {
                  tipoImpuesto = nombre
                  expanded = false
                  if (nombre != "Personalizado") {
                    impuestoPersonalizado = ""
                  }
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
              )
            }
          }
        }
        
        if (mostrarImpuestoPersonalizado) {
          Spacer(modifier = Modifier.height(8.dp))
          
          OutlinedTextField(
            value = impuestoPersonalizado,
            onValueChange = { newValue ->
              if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                impuestoPersonalizado = newValue
              }
            },
            label = { Text("Porcentaje de impuesto (%)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ej: 15.5") },
            isError = impuestoPersonalizado.isNotEmpty() && impuestoPersonalizado.toDoubleOrNull() == null,
            supportingText = {
              if (impuestoPersonalizado.isNotEmpty() && impuestoPersonalizado.toDoubleOrNull() == null) {
                Text("Ingrese un número válido", color = MaterialTheme.colorScheme.error)
              }
            }
          )
        }
        
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
              
              val porcentajeUsado = if (tipoImpuesto == "Personalizado") {
                impuestoPersonalizado.toDoubleOrNull() ?: 0.0
              } else {
                tiposImpuesto.find { it.first == tipoImpuesto }?.second ?: 21.0
              }
              
              Text(
                text = "Impuesto aplicado: ${porcentajeUsado}%",
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
              )
              Text("Total con impuesto: ${formatearPeso(results.first)}", fontWeight = FontWeight.Bold)
              Text("Solo impuesto: ${formatearPeso(results.second)}", fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }
}