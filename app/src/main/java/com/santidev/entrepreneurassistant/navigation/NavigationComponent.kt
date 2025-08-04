package com.santidev.entrepreneurassistant.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.santidev.entrepreneurassistant.ui.screens.CalculatorsScreen
import com.santidev.entrepreneurassistant.ui.screens.HomeScreen
//import com.santidev.entrepreneurassistant.ui.screens.SettingsScreen
import com.santidev.entrepreneurassistant.utils.BottomNavigation

@Composable
fun NavigationComponent() {
  val navController = rememberNavController()
  Scaffold(
    bottomBar = {
      BottomNavigation(navController = navController)
    }
  ) { paddingValues ->
    NavHost(
      navController = navController,
      startDestination = HomeScreen,
      modifier = Modifier.padding(paddingValues),
    ) {
      composable<HomeScreen> {
        HomeScreen(navController = navController)
      }
      composable<CalculatorScreen> {
        CalculatorsScreen(navController = navController)
      }
//      composable<SettingsScreen> {
//        SettingsScreen(navController = navController)
//      }
    }
  }
}