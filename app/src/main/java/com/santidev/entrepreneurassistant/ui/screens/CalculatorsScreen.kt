package com.santidev.entrepreneurassistant.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.santidev.entrepreneurassistant.utils.calculators.InflationCalculator
import com.santidev.entrepreneurassistant.utils.calculators.MarginCalculator
import com.santidev.entrepreneurassistant.utils.calculators.TaxesCalculator
import com.santidev.entrepreneurassistant.utils.composables.Reusable.CalculatorHeader
import kotlinx.coroutines.launch

@Composable
fun CalculatorsScreen(navController: NavHostController, initialTab: Int = 0) {
  
  val tabs = remember { listOf("Márgenes", "Inflación", "Impuestos") }
  val pagerState = rememberPagerState(
    initialPage = initialTab,
    pageCount = { tabs.size }
  )
  
  val coroutineScope = rememberCoroutineScope()
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    CalculatorHeader()
    
    TabRow(
      selectedTabIndex = pagerState.currentPage,
      modifier = Modifier.fillMaxWidth()
    ) {
      tabs.forEachIndexed { index, titulo ->
        Tab(
          selected = pagerState.currentPage == index,
          onClick = {
            coroutineScope.launch {
              pagerState.animateScrollToPage(index)
            }
          },
          text = { Text(titulo) },
          modifier = Modifier.padding(bottom = 16.dp)
        )
      }
    }
    Spacer(modifier = Modifier.height(16.dp))
    
    HorizontalPager(
      state = pagerState,
      modifier = Modifier.fillMaxSize(),
      beyondViewportPageCount = 1
    ) { page ->
      when(page) {
        0 -> MarginCalculator()
        1 -> InflationCalculator()
        2 -> TaxesCalculator()
      }
    }
  }
}