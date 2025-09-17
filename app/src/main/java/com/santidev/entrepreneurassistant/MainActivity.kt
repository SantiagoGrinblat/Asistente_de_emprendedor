package com.santidev.entrepreneurassistant

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.santidev.entrepreneurassistant.navigation.NavigationComponent
import com.santidev.entrepreneurassistant.ui.theme.BackgroundColor
import com.santidev.entrepreneurassistant.ui.theme.EntrepreneurAssistantTheme
import com.santidev.entrepreneurassistant.utils.BottomNavigation

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    HideBars()
    
    enableEdgeToEdge()
    
    setContent {
      EntrepreneurAssistantTheme {
        val navController = rememberNavController()
        
        Scaffold(
          containerColor = Color.Transparent,
          bottomBar = {
            BottomNavigation(navController = navController)
          }
        ) { paddingValues ->
          NavigationComponent(
            navController = navController,
            paddingValues = paddingValues
          )
        }
      }
    }
  }
  
  private fun HideBars() {
    // Para que el contenido se extienda debajo de las barras del sistema
    WindowCompat.setDecorFitsSystemWindows(window, false)
    
    // Obtener el WindowInsetsController
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    
    windowInsetsController?.let { controller ->
      // Ocultar ambas barras superior E inferior
      controller.hide(WindowInsetsCompat.Type.systemBars())
      
      // Configurar el comportamiento: las barras aparecen temporalmente al deslizar
      controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
    
    // Usarlo en versiones anteriores a Android 11 (API 30)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
      @Suppress("DEPRECATION")
      window.decorView.systemUiVisibility = (
          View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
              View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
              View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
              View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
              View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
              View.SYSTEM_UI_FLAG_FULLSCREEN
          )
    }
  }
  
  // Metodo opcional para restaurar las barras si es necesario
  private fun showSystemBars() {
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController?.show(WindowInsetsCompat.Type.systemBars())
  }
  
  override fun onResume() {
    super.onResume()
    // Las barras permanecen ocultas al volver a la app cuando se sale
    HideBars()
  }
}