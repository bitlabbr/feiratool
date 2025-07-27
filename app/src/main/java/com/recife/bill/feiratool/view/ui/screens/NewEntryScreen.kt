package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.recife.bill.feiratool.view.ui.components.AddEntryForm
import com.recife.bill.feiratool.view.ui.components.CardDefault
import com.recife.bill.feiratool.view.ui.components.CustomDivider
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel

@Composable
fun NewEntryScreen(
    mainViewModel: AirPowerViewModel,
    listId: String,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        CardDefault(
            layouts = listOf {
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Novo item",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = tb_primary_light
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 15.dp))

                CustomDivider()

                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                AddEntryForm(
                    mainViewModel = mainViewModel,
                    listId = listId,
                    navController = navController
                )
            }
        )
    }
}