package com.santidev.entrepreneurassistant.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
  primary = LightGreen,
  onPrimary = Color.White,
  
  // Colores de fondo
  background = Color.Transparent,
  onBackground = WhiteText,
  
  // Colores de superficie
  surface = Color.Transparent,
  onSurface = WhiteText,
  
  // Colores secundarios
  secondary = LightGreen,
  onSecondary = WhiteText,
  
  // Agregar más colores de texto para consistencia
  onTertiary = WhiteText,
  onPrimaryContainer = WhiteText,
  onSecondaryContainer = WhiteText,
  onTertiaryContainer = WhiteText,
  onError = WhiteText,
  onErrorContainer = WhiteText,
  onSurfaceVariant = WhiteText,
  outline = WhiteText.copy(alpha = 0.6f),
  outlineVariant = WhiteText.copy(alpha = 0.4f),
)

private val LightColorScheme = lightColorScheme(
  primary = LightGreen,
  onPrimary = WhiteText,
  
  // Colores de fondo
  background = Color.Transparent,
  onBackground = WhiteText,
  
  // Colores de superficie
  surface = Color.Transparent,
  onSurface = WhiteText,
  
  // Colores secundarios
  secondary = LightGreen,
  onSecondary = WhiteText,
  
  // Agregar más colores de texto para consistencia
  onTertiary = WhiteText,
  onPrimaryContainer = WhiteText,
  onSecondaryContainer = WhiteText,
  onTertiaryContainer = WhiteText,
  onError = WhiteText,
  onErrorContainer = WhiteText,
  onSurfaceVariant = WhiteText,
  outline = WhiteText.copy(alpha = 0.6f),
  outlineVariant = WhiteText.copy(alpha = 0.4f),
)

@Composable
fun EntrepreneurAssistantTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }
  
  // Aplicar el fondo personalizado aquí
  Box(modifier = Modifier.fillMaxSize()) {
    BackgroundColor()
    
    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
  }
}