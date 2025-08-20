package com.santidev.entrepreneurassistant.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BackgroundColor() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(
        brush = Brush.linearGradient(
          colors = listOf(
            Color(0xFF0B1D13), // Verde muy oscuro arriba
            Color(0xFF000000), // Negro abajo
          )
        )
      )
  ) {
    // Agregamos un radial para simular el "brillo" verde arriba a la izquierda
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          brush = Brush.radialGradient(
            colors = listOf(
              Color(0xFF6D826B).copy(alpha = 0.6f), // Verde difuso
              Color.Transparent
            ),
            center = androidx.compose.ui.geometry.Offset(200f, 200f),
            radius = 600f
          )
        )
    )
  }
}