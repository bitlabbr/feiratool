package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.CommonConstants
import com.ifpe.edu.br.common.components.CustomColumn
import com.recife.bill.feiratool.view.ui.components.ShoppListCard
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel

@Composable
fun ListsScreen(
    navController: NavHostController,
    mainViewModel: AirPowerViewModel
) {
    LaunchedEffect(Unit) {
        mainViewModel.loadAllShoppingLists()
    }

    val scrollState = rememberScrollState()
    val shopListFlow = mainViewModel.getShoppLists().collectAsState()

    CustomColumn(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
        alignmentStrategy = CommonConstants.Ui.ALIGNMENT_TOP,
        layouts = listOf {
            shopListFlow.value.forEach {
                item ->
                ShoppListCard(
                    navController,
                    item
                )
            }
        }
    )
}