package com.santidev.entrepreneurassistant.utils.composables.calculatesFunctions

import kotlin.math.pow

fun calcularMargenSobreCosto(costo: Double, margen: Double): Pair<Double, Double> {
  if (costo <= 0 || margen <= 0) return Pair(0.0, 0.0)
  val precioVenta = costo * (1 + margen / 100)
  val ganancia = precioVenta - costo
  return Pair(precioVenta, ganancia)
}

fun calcularMargenSobrePrecio(costo: Double, margen: Double): Pair<Double, Double> {
  if (costo <= 0 || margen <= 0) return Pair(0.0, 0.0)
  val precioVenta = costo / (1 - margen / 100)
  val ganancia = precioVenta - costo
  return Pair(precioVenta, ganancia)
}

fun calcularInflacion(original: Double, inflacion: Double, meses: Double): Triple<Double, Double, String> {
  if (original <= 0 || inflacion <= 0 || meses <= 0) return Triple(0.0, 0.0, "0.0")
  val ajustado = original * (1 + inflacion / 100).pow(meses)
  val aumento = ajustado - original
  val porcentajeAumento = String.format("%.1f", (aumento / original * 100))
  return Triple(ajustado, aumento, porcentajeAumento)
}

fun calcularImpuestos(monto: Double, porcentaje: Double): Pair<Double, Double> {
  if (monto <= 0 || porcentaje < 0) return Pair(0.0, 0.0)
  val impuesto = monto * porcentaje / 100
  val total = monto + impuesto
  return Pair(total, impuesto)
}