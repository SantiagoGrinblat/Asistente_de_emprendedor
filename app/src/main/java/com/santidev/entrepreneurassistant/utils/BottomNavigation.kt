package com.santidev.entrepreneurassistant.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.santidev.entrepreneurassistant.navigation.CalculatorScreen
import com.santidev.entrepreneurassistant.navigation.HomeScreen
import com.santidev.entrepreneurassistant.navigation.SettingsScreen
import com.santidev.entrepreneurassistant.ui.screens.CalculatorsScreen

//data class BottomNavigationItem(
//  val title: String,
//  val destination: Any,
//  val selectedIcon: ImageVector,
//  val unselectedIcon: ImageVector,
//)
//
//@Composable
//fun BottomNavigation(navController: NavHostController) {
//  val items = listOf(
//    BottomNavigationItem(
//      title = "Home",
//      destination = HomeScreen,
//      selectedIcon = Icons.Filled.Home,
//      unselectedIcon = Icons.Outlined.Home
//    ),
//    BottomNavigationItem(
//      title = "Calculator",
//      destination = CalculatorScreen,
//      selectedIcon = Icons.Filled.ShoppingCart,
//      unselectedIcon = Icons.Outlined.ShoppingCart
//    ),
//    BottomNavigationItem(
//      title = "Settings",
//      destination = SettingsScreen,
//      selectedIcon = Icons.Filled.Settings,
//      unselectedIcon = Icons.Outlined.Settings
//    ),
//  )
//
//  var selectedItemIndex by rememberSaveable {
//    mutableStateOf(0)
//  }
//
//  Scaffold(
//    bottomBar = {
//      NavigationBar {
//        items.forEachIndexed { index, item ->
//          NavigationBarItem(
//            selected = selectedItemIndex == index,
//            onClick = {
//              selectedItemIndex = index
//              navController.navigate(item.destination) {
//                popUpTo(navController.graph.startDestinationId) {
//                  saveState = true
//                }
//                launchSingleTop = true
//                restoreState = true
//              }
//            },
//            label = {
//              Text(text = item.title)
//            },
//            icon = {
//              Icon(
//                imageVector = if (index == selectedItemIndex) {
//                  item.selectedIcon
//                } else item.unselectedIcon ,
//                contentDescription = item.title
//              )
//            }
//          )
//        }
//      }
//    }
//  ) {}
//}

//data class BottomNavigationItem(
//  val title: String,
//  val destination: Any,
//  val selectedIcon: ImageVector,
//  val unselectedIcon: ImageVector,
//)
//
//@Composable
//fun BottomNavigation(navController: NavHostController) {
//  val items = listOf(
//    BottomNavigationItem(
//      title = "Home",
//      destination = HomeScreen,
//      selectedIcon = Icons.Filled.Home,
//      unselectedIcon = Icons.Outlined.Home
//    ),
//    BottomNavigationItem(
//      title = "Calculator",
//      destination = CalculatorScreen,
//      selectedIcon = Icons.Filled.ShoppingCart,
//      unselectedIcon = Icons.Outlined.ShoppingCart
//    ),
//    BottomNavigationItem(
//      title = "Settings",
//      destination = SettingsScreen,
//      selectedIcon = Icons.Filled.Settings,
//      unselectedIcon = Icons.Outlined.Settings
//    ),
//  )
//
//  // Obtenemos la ruta actual para determinar cual item esta seleccionado
//  val currentDestination = navController.currentBackStackEntryAsState().value?.destination
//
//  NavigationBar {
//    items.forEach { item ->
//      // Verificamos si el item actual está seleccionando el correcto basandose en la ruta de la layoutr
//      val isSelected = currentDestination?.route == item.destination::class.qualifiedName
//
//      NavigationBarItem(
//        selected = isSelected,
//        onClick = {
//          navController.navigate(item.destination) {
//            // Evitamos múltiples copias de la misma pantalla en el stack
//            popUpTo(navController.graph.startDestinationId) {
//              saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//          }
//        },
//        label = {
//          Text(text = item.title)
//        },
//        icon = {
//          Icon(
//            imageVector = if (isSelected) {
//              item.selectedIcon
//            } else {
//              item.unselectedIcon
//            },
//            contentDescription = item.title
//          )
//        }
//      )
//    }
//  }
//}

data class BottomNavigationItem(
  val title: String,
  val destination: Any,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
)

@Composable
fun BottomNavigation(navController: NavHostController) {
  // Memorizar la lista de items para evitar recreación
  val items = remember {
    listOf(
      BottomNavigationItem(
        title = "Home",
        destination = HomeScreen,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
      ),
      BottomNavigationItem(
        title = "Calculator",
        destination = CalculatorScreen,
        selectedIcon = Icons.Filled.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart
      ),
      BottomNavigationItem(
        title = "Settings",
        destination = SettingsScreen,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
      ),
    )
  }
  
  // Obtener la ruta actual de forma eficiente
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  
  NavigationBar {
    items.forEach { item ->
      val isSelected = currentDestination?.route == item.destination::class.qualifiedName
      
      NavigationBarItem(
        selected = isSelected,
        onClick = {
          // Evitar navegación innecesaria si ya estamos en la pantalla
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
          Text(text = item.title)
        },
        icon = {
          Icon(
            imageVector = if (isSelected) {
              item.selectedIcon
            } else {
              item.unselectedIcon
            },
            contentDescription = item.title
          )
        }
      )
    }
  }
}