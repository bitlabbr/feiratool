package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.CommonConstants
import com.ifpe.edu.br.common.components.CustomColumn
import com.ifpe.edu.br.common.components.CustomInputText
import com.ifpe.edu.br.common.components.RectButton
import com.ifpe.edu.br.common.ui.theme.White
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel

@Composable
fun NewListScreen(
    navController: NavHostController,
    mainViewModel: AirPowerViewModel
) {
    val scrollState = rememberScrollState()

    CustomColumn(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
        alignmentStrategy = CommonConstants.Ui.ALIGNMENT_TOP,
        layouts = listOf {
            var listName by rememberSaveable { mutableStateOf("") }
            CustomInputText(
                value = listName,
                onValueChange = { listName = it },
                label = "Nome da lista",
                placeholder = "Digite o nome da lista",
                inputFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = tb_primary_light,
                    unfocusedTextColor = tb_primary_light,
                    focusedLabelColor = tb_primary_light,
                    unfocusedLabelColor = tb_primary_light,
                    focusedContainerColor = White,
                    unfocusedContainerColor = White
                ),
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            RectButton(
                text = "Criar",
                onClick = {
                    mainViewModel.createList(listName)
                }
            )
        }
    )
}