package com.santidev.entrepreneurassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.santidev.entrepreneurassistant.ui.theme.EntrepreneurAssistantTheme
import com.santidev.entrepreneurassistant.utils.Calculators

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      EntrepreneurAssistantTheme {
        Calculators()
//        CalculadorasEmprendedor()
//        AppNavigation()
      }
    }
  }
}
//@Composable
//fun CalculadorasEmprendedor() {
//  var tabSeleccionada by remember { mutableStateOf(0) }
//
//  val tabs = listOf(
//    "Margen",
//    "Punto Equilibrio",
//    "Inflación",
//    "Impuestos"
//  )
//
//  Column(
//    modifier = Modifier
//      .fillMaxSize()
//      .padding(16.dp)
//  ) {
//    Text(
//      text = "Calculadoras para Emprendedores",
//      fontSize = 24.sp,
//      fontWeight = FontWeight.Bold,
//      modifier = Modifier
//        .fillMaxWidth()
//        .padding(bottom = 16.dp),
//      textAlign = TextAlign.Center
//    )
//
//    // Tabs
//    TabRow(selectedTabIndex = tabSeleccionada) {
//      tabs.forEachIndexed { index, titulo ->
//        Tab(
//          selected = tabSeleccionada == index,
//          onClick = { tabSeleccionada = index },
//          text = { Text(titulo) }
//        )
//      }
//    }
//
//    Spacer(modifier = Modifier.height(16.dp))
//
//    // Contenido según tab seleccionada
//    when (tabSeleccionada) {
//      0 -> CalculadoraMargen()
//      1 -> CalculadoraPuntoEquilibrio()
//      2 -> CalculadoraInflacion()
//      3 -> CalculadoraImpuestos()
//    }
//  }
//}
//
//@Composable
//fun CalculadoraMargen() {
//  var costo by remember { mutableStateOf("") }
//  var margenDeseado by remember { mutableStateOf("") }
//  var precioVenta by remember { mutableStateOf(0.0) }
//  var ganancia by remember { mutableStateOf(0.0) }
//
//  fun calcular() {
//    val costoNum = costo.toDoubleOrNull() ?: 0.0
//    val margenNum = margenDeseado.toDoubleOrNull() ?: 0.0
//
//    if (costoNum > 0 && margenNum > 0) {
//      precioVenta = costoNum / (1 - margenNum / 100)
//      ganancia = precioVenta - costoNum
//    }
//  }
//
//  Column(
//    modifier = Modifier
//      .fillMaxWidth()
//      .verticalScroll(rememberScrollState())
//  ) {
//    Card(
//      modifier = Modifier.fillMaxWidth()
//    ) {
//      Column(
//        modifier = Modifier.padding(16.dp)
//      ) {
//        Text(
//          text = "Calculadora de Precio con Margen",
//          fontSize = 18.sp,
//          fontWeight = FontWeight.SemiBold,
//          modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        OutlinedTextField(
//          value = costo,
//          onValueChange = { costo = it },
//          label = { Text("Costo del producto ($)") },
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//          modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//          value = margenDeseado,
//          onValueChange = { margenDeseado = it },
//          label = { Text("Margen deseado (%)") },
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//          modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//          onClick = { calcular() },
//          modifier = Modifier.fillMaxWidth()
//        ) {
//          Text("Calcular")
//        }
//
//        if (precioVenta > 0) {
//          Spacer(modifier = Modifier.height(16.dp))
//          Card(
//            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
//          ) {
//            Column(
//              modifier = Modifier.padding(16.dp)
//            ) {
//              Text(
//                text = "Resultados:",
//                fontWeight = FontWeight.SemiBold,
//                modifier = Modifier.padding(bottom = 8.dp)
//              )
//              Text("Precio de venta: ${formatearPeso(precioVenta)}")
//              Text("Ganancia: ${formatearPeso(ganancia)}")
//              Text("Ganancia %: ${String.format("%.1f", (ganancia / precioVenta * 100))}%")
//            }
//          }
//        }
//      }
//    }
//  }
//}
//
//@Composable
//fun CalculadoraPuntoEquilibrio() {
//  var costosFijos by remember { mutableStateOf("") }
//  var costosVariables by remember { mutableStateOf("") }
//  var precioUnitario by remember { mutableStateOf("") }
//  var puntosEquilibrio by remember { mutableStateOf(0.0) }
//  var ventasNecesarias by remember { mutableStateOf(0.0) }
//
//  fun calcular() {
//    val fijos = costosFijos.toDoubleOrNull() ?: 0.0
//    val variables = costosVariables.toDoubleOrNull() ?: 0.0
//    val precio = precioUnitario.toDoubleOrNull() ?: 0.0
//
//    if (fijos > 0 && precio > variables) {
//      puntosEquilibrio = fijos / (precio - variables)
//      ventasNecesarias = puntosEquilibrio * precio
//    }
//  }
//
//  Column(
//    modifier = Modifier
//      .fillMaxWidth()
//      .verticalScroll(rememberScrollState())
//  ) {
//    Card(
//      modifier = Modifier.fillMaxWidth()
//    ) {
//      Column(
//        modifier = Modifier.padding(16.dp)
//      ) {
//        Text(
//          text = "Calculadora de Punto de Equilibrio",
//          fontSize = 18.sp,
//          fontWeight = FontWeight.SemiBold,
//          modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        OutlinedTextField(
//          value = costosFijos,
//          onValueChange = { costosFijos = it },
//          label = { Text("Costos fijos mensuales ($)") },
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//          modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//          value = costosVariables,
//          onValueChange = { costosVariables = it },
//          label = { Text("Costo variable por unidad ($)") },
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//          modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//          value = precioUnitario,
//          onValueChange = { precioUnitario = it },
//          label = { Text("Precio de venta por unidad ($)") },
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//          modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//          onClick = { calcular() },
//          modifier = Modifier.fillMaxWidth()
//        ) {
//          Text("Calcular")
//        }
//
//        if (puntosEquilibrio > 0) {
//          Spacer(modifier = Modifier.height(16.dp))
//          Card(
//            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
//          ) {
//            Column(
//              modifier = Modifier.padding(16.dp)
//            ) {
//              Text(
//                text = "Resultados:",
//                fontWeight = FontWeight.SemiBold,
//                modifier = Modifier.padding(bottom = 8.dp)
//              )
//              Text("Unidades a vender: ${String.format("%.0f", puntosEquilibrio)} por mes")
//              Text("Ventas necesarias: ${formatearPeso(ventasNecesarias)} por mes")
//              Text("Unidades por día: ${String.format("%.1f", puntosEquilibrio / 30)}")
//            }
//          }
//        }
//      }
//    }
//  }
//}
//
//@Composable
//fun CalculadoraInflacion() {
//  var precioOriginal by remember { mutableStateOf("") }
//  var inflacionMensual by remember { mutableStateOf("") }
//  var meses by remember { mutableStateOf("") }
//  var precioAjustado by remember { mutableStateOf(0.0) }
//  var aumento by remember { mutableStateOf(0.0) }
//
//  fun calcular() {
//    val original = precioOriginal.toDoubleOrNull() ?: 0.0
//    val inflacion = inflacionMensual.toDoubleOrNull() ?: 0.0
//    val mesesNum = meses.toDoubleOrNull() ?: 0.0
//
//    if (original > 0 && inflacion > 0 && mesesNum > 0) {
//      precioAjustado = original * (1 + inflacion / 100).pow(mesesNum)
//      aumento = precioAjustado - original
//    }
//  }
//
//  Column(
//    modifier = Modifier
//      .fillMaxWidth()
//      .verticalScroll(rememberScrollState())
//  ) {
//    Card(
//      modifier = Modifier.fillMaxWidth()
//    ) {
//      Column(
//        modifier = Modifier.padding(16.dp)
//      ) {
//        Text(
//          text = "Calculadora de Ajuste por Inflación",
//          fontSize = 18.sp,
//          fontWeight = FontWeight.SemiBold,
//          modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        OutlinedTextField(
//          value = precioOriginal,
//          onValueChange = { precioOriginal = it },
//          label = { Text("Precio original ($)") },
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//          modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//          value = inflacionMensual,
//          onValueChange = { inflacionMensual = it },
//          label = { Text("Inflación mensual (%)") },
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//          modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//          value = meses,
//          onValueChange = { meses = it },
//          label = { Text("Cantidad de meses") },
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//          modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//          onClick = { calcular() },
//          modifier = Modifier.fillMaxWidth()
//        ) {
//          Text("Calcular")
//        }
//
//        if (precioAjustado > 0) {
//          Spacer(modifier = Modifier.height(16.dp))
//          Card(
//            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
//          ) {
//            Column(
//              modifier = Modifier.padding(16.dp)
//            ) {
//              Text(
//                text = "Resultados:",
//                fontWeight = FontWeight.SemiBold,
//                modifier = Modifier.padding(bottom = 8.dp)
//              )
//              Text("Precio ajustado: ${formatearPeso(precioAjustado)}")
//              Text("Aumento total: ${formatearPeso(aumento)}")
//              Text("Aumento %: ${String.format("%.1f", (aumento / precioOriginal.toDouble() * 100))}%")
//            }
//          }
//        }
//      }
//    }
//  }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CalculadoraImpuestos() {
//  var montoBase by remember { mutableStateOf("") }
//  var tipoImpuesto by remember { mutableStateOf("IVA 21%") }
//  var expanded by remember { mutableStateOf(false) }
//  var montoConImpuesto by remember { mutableStateOf(0.0) }
//  var soloImpuesto by remember { mutableStateOf(0.0) }
//
//  val tiposImpuesto = listOf(
//    "IVA 21%" to 21.0,
//    "IVA 10.5%" to 10.5,
//    "Ingresos Brutos 3%" to 3.0,
//    "Ganancias 35%" to 35.0
//  )
//
//  fun calcular() {
//    val base = montoBase.toDoubleOrNull() ?: 0.0
//    val porcentaje = tiposImpuesto.find { it.first == tipoImpuesto }?.second ?: 21.0
//
//    if (base > 0) {
//      soloImpuesto = base * porcentaje / 100
//      montoConImpuesto = base + soloImpuesto
//    }
//  }
//
//  Column(
//    modifier = Modifier
//      .fillMaxWidth()
//      .verticalScroll(rememberScrollState())
//  ) {
//    Card(
//      modifier = Modifier.fillMaxWidth()
//    ) {
//      Column(
//        modifier = Modifier.padding(16.dp)
//      ) {
//        Text(
//          text = "Calculadora de Impuestos",
//          fontSize = 18.sp,
//          fontWeight = FontWeight.SemiBold,
//          modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        OutlinedTextField(
//          value = montoBase,
//          onValueChange = { montoBase = it },
//          label = { Text("Monto base ($)") },
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//          modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        ExposedDropdownMenuBox(
//          expanded = expanded,
//          onExpandedChange = { expanded = !expanded }
//        ) {
//          OutlinedTextField(
//            value = tipoImpuesto,
//            onValueChange = { },
//            readOnly = true,
//            label = { Text("Tipo de impuesto") },
//            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//            modifier = Modifier
//              .menuAnchor()
//              .fillMaxWidth()
//          )
//          ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//          ) {
//            tiposImpuesto.forEach { (nombre, _) ->
//              DropdownMenuItem(
//                text = { Text(nombre) },
//                onClick = {
//                  tipoImpuesto = nombre
//                  expanded = false
//                }
//              )
//            }
//          }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//          onClick = { calcular() },
//          modifier = Modifier.fillMaxWidth()
//        ) {
//          Text("Calcular")
//        }
//
//        if (montoConImpuesto > 0) {
//          Spacer(modifier = Modifier.height(16.dp))
//          Card(
//            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
//          ) {
//            Column(
//              modifier = Modifier.padding(16.dp)
//            ) {
//              Text(
//                text = "Resultados:",
//                fontWeight = FontWeight.SemiBold,
//                modifier = Modifier.padding(bottom = 8.dp)
//              )
//              Text("Monto con impuesto: ${formatearPeso(montoConImpuesto)}")
//              Text("Solo impuesto: ${formatearPeso(soloImpuesto)}")
//            }
//          }
//        }
//      }
//    }
//  }
//}
//
//fun formatearPeso(valor: Double): String {
//  val formato = NumberFormat.getCurrencyInstance(Locale("es", "AR"))
//  return formato.format(valor)
//}