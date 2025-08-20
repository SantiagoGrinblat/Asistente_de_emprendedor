package com.santidev.entrepreneurassistant

import android.os.Bundle
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
import androidx.navigation.compose.rememberNavController
import com.santidev.entrepreneurassistant.navigation.NavigationComponent
import com.santidev.entrepreneurassistant.ui.theme.BackgroundColor
import com.santidev.entrepreneurassistant.ui.theme.EntrepreneurAssistantTheme
import com.santidev.entrepreneurassistant.utils.BottomNavigation

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
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
}