package com.santidev.entrepreneurassistant.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdUnits
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.BorderOuter
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AdUnits
import androidx.compose.material.icons.outlined.AllInbox
import androidx.compose.material.icons.outlined.BorderOuter
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.santidev.entrepreneurassistant.R
import com.santidev.entrepreneurassistant.navigation.CalculatorScreen
import com.santidev.entrepreneurassistant.navigation.HomeScreen
import com.santidev.entrepreneurassistant.navigation.SettingsScreen

data class BottomNavigationItem(
  val title: String,
  val destination: Any,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
)

@Composable
fun BottomNavigation(navController: NavHostController) {
  // Recordar la lista de items para evitar recreación de la misma en cada layout
  val items = remember {
    listOf(
      BottomNavigationItem(
        title = "Inicio",
        destination = HomeScreen,
        selectedIcon = Icons.Filled.Layers,
        unselectedIcon = Icons.Outlined.Layers
      ),
      BottomNavigationItem(
        title = "Calculadora",
        destination = CalculatorScreen,
        selectedIcon = Icons.Filled.Calculate,
        unselectedIcon = Icons.Outlined.Calculate
      ),
      BottomNavigationItem(
        title = "Ajustes",
        destination = SettingsScreen,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
      ),
    )
  }
  
  // Obtener la ruta de navegacion actual de forma mas facil
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  
  NavigationBar(
    modifier = Modifier
      .fillMaxWidth(),
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    tonalElevation = 8.dp
  ) {
    items.forEach { item ->
      val isSelected = currentDestination?.route == item.destination::class.qualifiedName
      
      NavigationBarItem(
        selected = isSelected,
        onClick = {
          // Evitar navegación innecesaria si ya estamos en esa layout para evitar recarga
          if (!isSelected) {
            navController.navigate(item.destination) {
              popUpTo(navController.graph.startDestinationId) {
                saveState = true
              }
              launchSingleTop = true
              restoreState = true
            }
          }
        },
        label = {
          Text(
            text = item.title,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) {
              MaterialTheme.colorScheme.primary
            } else {
              MaterialTheme.colorScheme.onSurface
            }
          )
        },
        icon = {
          Icon(
            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.title,
            tint = if (isSelected) {
              MaterialTheme.colorScheme.primary
            } else {
              MaterialTheme.colorScheme.onSurfaceVariant
            }
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = MaterialTheme.colorScheme.primary,
          selectedTextColor = MaterialTheme.colorScheme.primary,
          unselectedIconColor = MaterialTheme.colorScheme.onSurface,
          unselectedTextColor = MaterialTheme.colorScheme.onSurface,
          indicatorColor = MaterialTheme.colorScheme.surface
        )
      )
    }
  }
}
