package com.santidev.entrepreneurassistant.utils.calculators

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.santidev.entrepreneurassistant.utils.composables.Reusable.CalculatorCards
import com.santidev.entrepreneurassistant.utils.composables.calculatesFunctions.CalculatorState
import com.santidev.entrepreneurassistant.utils.composables.calculatesFunctions.calcularMargenSobrePrecio

@Composable
fun CalculatePriceSale() {
  
  var state by remember { mutableStateOf(CalculatorState()) }
  
  val results: Pair<Double, Double> by remember(state.input1, state.input2) {
    derivedStateOf {
      calcularMargenSobrePrecio(
        state.input1.toDoubleOrNull() ?: 0.0,
        state.input2.toDoubleOrNull() ?: 0.0
      )
    }
  }
  
  CalculatorCards(
    title = "Calculadora de Precios con Margen sobre el Precio de Venta",
    subTitle = "* Ideal para comercios minoristas que calculan su ganancia como un porcentaje del precio final.*",
    input1Label = "Costo del producto ($)",
    input1Value = state.input1,
    onInput1Change = { state = state.copy(input1 = it) },
    input2Label = "Margen deseado (%)",
    input2Value = state.input2,
    onInput2change = { state = state.copy(input2 = it) },
    onCalculate = {
      state = state.copy(
        result1 = results.first,
        result2 = results.second
      )
    },
    showResults = state.result1 > 0,
    result1Label = "Precio de venta",
    result1Value = state.result1,
    result2Label = "Ganancia",
    result2Value = state.result2
  )
  
}