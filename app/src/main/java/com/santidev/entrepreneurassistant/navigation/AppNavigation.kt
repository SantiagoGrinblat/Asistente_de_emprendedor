package com.santidev.entrepreneurassistant.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.santidev.entrepreneurassistant.navigation.BalanceScreen
import com.santidev.entrepreneurassistant.navigation.InflationScreen
import com.santidev.entrepreneurassistant.navigation.MarginScreen
import com.santidev.entrepreneurassistant.navigation.MenuScreen
import com.santidev.entrepreneurassistant.navigation.TaxesScreen
import com.santidev.entrepreneurassistant.ui.screens.BalanceScreen
import com.santidev.entrepreneurassistant.ui.screens.InflationScreen
import com.santidev.entrepreneurassistant.ui.screens.MarginScreen
import com.santidev.entrepreneurassistant.ui.screens.MenuScreen
import com.santidev.entrepreneurassistant.ui.screens.TaxesScreen

@Composable
fun AppNavigation() {
  val navController = rememberNavController()
  
  NavHost(navController = navController, startDestination = MenuScreen) {
    composable<MenuScreen> {
      MenuScreen(navController = navController)
    }
    
    composable<MarginScreen> {
      MarginScreen(navController = navController)
    }
    
    composable<BalanceScreen> {
      BalanceScreen(navController = navController)
    }
    
    composable<InflationScreen> {
      InflationScreen(navController = navController)
    }
    
    composable<TaxesScreen> {
      TaxesScreen(navController = navController)
    }
  }
}