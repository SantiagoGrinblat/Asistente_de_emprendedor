package com.santidev.entrepreneurassistant.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.santidev.entrepreneurassistant.ui.screens.CalculatorsScreen
import com.santidev.entrepreneurassistant.ui.screens.HomeScreen
import com.santidev.entrepreneurassistant.ui.screens.SettingsScreen
import com.santidev.entrepreneurassistant.utils.BottomNavigation

//@Composable
//fun NavigationComponent() {
//  val navController = rememberNavController()
//
//  // Scaffold principal que contiene el NavigationBar
//  Scaffold(
//    bottomBar = {
//      BottomNavigation(navController = navController)
//    }
//  ) { paddingValues ->
//    // NavHost con padding para evitar que el contenido se superponga con el NavigationBar
//    NavHost(
//      navController = navController,
//      startDestination = HomeScreen,
//      modifier = Modifier.padding(paddingValues)
//    ) {
//      composable<HomeScreen> {
//        HomeScreen(navController = navController)
//      }
//      composable<CalculatorScreen> {
//        CalculatorsScreen(navController = navController)
//      }
//      composable<SettingsScreen> {
//        SettingsScreen(navController = navController)
//      }
//    }
//  }
//}

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
      // Optimización: Configurar transiciones más suaves
      enterTransition = { EnterTransition.None },
      exitTransition = { ExitTransition.None },
      popEnterTransition = { EnterTransition.None },
      popExitTransition = { ExitTransition.None }
    ) {
      composable<HomeScreen> {
        HomeScreen(navController = navController)
      }
      composable<CalculatorScreen> {
        CalculatorsScreen(navController = navController)
      }
      composable<SettingsScreen> {
        SettingsScreen(navController = navController)
      }
    }
  }
}