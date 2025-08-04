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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaxesCalculator() {
  var montoBase by remember { mutableStateOf("") }
  var tipoImpuesto by remember { mutableStateOf("IVA 21%") }
  var expanded by remember { mutableStateOf(false) }
  var montoConImpuesto by remember { mutableStateOf(0.0) }
  var soloImpuesto by remember { mutableStateOf(0.0) }
  
  // Nuevo estado para el impuesto personalizado para recordar el valor que seleccione el usuario
  var impuestoPersonalizado by remember { mutableStateOf("") }
  var mostrarCampoPersonalizado by remember { mutableStateOf(false) }
  
  val tiposImpuesto = listOf(
    "IVA 21%" to 21.0,
    "IVA 10.5%" to 10.5,
    "Ingresos Brutos 3%" to 3.0,
    "Ganancias 35%" to 35.0,
    "Personalizado" to 0.0 // Nueva opcion para el impuesto personalizado
  )
  
  fun calcular() {
    val base = montoBase.toDoubleOrNull() ?: 0.0
    
    val porcentaje = if (tipoImpuesto == "Personalizado") {
      val porcentajePersonalizado = impuestoPersonalizado.toDoubleOrNull()
      if (porcentajePersonalizado == null || porcentajePersonalizado < 0) {
        // Si no es válido, resetear resultados y salir
        montoConImpuesto = 0.0
        soloImpuesto = 0.0
        return
      }
      porcentajePersonalizado
    } else {
      tiposImpuesto.find { it.first == tipoImpuesto }?.second ?: 21.0
    }
    
    if (base > 0) {
      soloImpuesto = base * porcentaje / 100
      montoConImpuesto = base + soloImpuesto
    } else {
      // Si el monto base no es valido, resetear resultados a 0
      montoConImpuesto = 0.0
      soloImpuesto = 0.0
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
          text = "Calculadora de Impuestos",
          fontSize = 21.sp,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
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
                text = { Text(
                  nombre,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.primary
                ) },
                onClick = {
                  tipoImpuesto = nombre
                  mostrarCampoPersonalizado = (nombre == "Personalizado")
                  expanded = false
                  
                  // Borrar el campo personalizado si se selecciona otro tipo de porcentaje
                  if (nombre != "Personalizado") {
                    impuestoPersonalizado = ""
                  }
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.surface),
              )
            }
          }
        }
        
        // seccion para impuesto personalizado
        if (mostrarCampoPersonalizado) {
          Spacer(modifier = Modifier.height(8.dp))
          
          OutlinedTextField(
            value = impuestoPersonalizado,
            onValueChange = { newValue ->
              // Validar que solo se ingresen numeros y puntos
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
        
        Button(
          onClick = { calcular() },
          modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
          shape = RoundedCornerShape(10),
          elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp)
        ) {
          Text("Calcular")
        }
        
        if (montoConImpuesto > 0) {
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
              
              // Mostrar el porcentaje usado en los resultados
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
              Text("Total con impuesto: ${formatearPeso(montoConImpuesto)}", fontWeight = FontWeight.Bold)
              Text("Solo impuesto: ${formatearPeso(soloImpuesto)}", fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }
}