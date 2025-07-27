package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.recife.bill.feiratool.view.ui.components.AddEntryForm
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel

@Composable
fun AddEntryScreen(
    mainViewModel: AirPowerViewModel,
    listId: String,
    navController: NavHostController
) {
    AddEntryForm(
        mainViewModel = mainViewModel,
        listId = listId,
        navController = navController
    )
}