package com.santidev.entrepreneurassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.santidev.entrepreneurassistant.navigation.NavigationComponent
import com.santidev.entrepreneurassistant.ui.theme.EntrepreneurAssistantTheme
import com.santidev.entrepreneurassistant.utils.BottomNavigation

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      EntrepreneurAssistantTheme {
        Box(modifier = Modifier.fillMaxSize()) {
          NavigationComponent()
        }
      }
    }
  }
}