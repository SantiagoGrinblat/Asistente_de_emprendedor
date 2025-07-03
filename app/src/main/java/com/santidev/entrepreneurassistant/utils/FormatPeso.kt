package com.santidev.entrepreneurassistant.utils

import androidx.compose.runtime.Composable
import java.text.NumberFormat
import java.util.Locale

@Composable
fun formatearPeso(valor: Double): String {
  val formato = NumberFormat.getCurrencyInstance(Locale("es", "AR"))
  return formato.format(valor)
}