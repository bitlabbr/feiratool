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
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppList
import com.recife.bill.feiratool.model.utils.AirPowerUtil
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel
import java.util.UUID

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
            var budget by rememberSaveable { mutableStateOf("0.0") }
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

            CustomInputText(
                value = budget,
                onValueChange = { budget = it },
                label = "Orçamento da lista",
                placeholder = "Digite o valor do orçamento",
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
                    var intBudget: Double = 0.0
                    try {
                        intBudget = budget.toDouble()
                    } catch (e: Exception) {

                    }
                    val newList = ShoppList(
                        id = UUID.randomUUID().toString(),
                        name = listName,
                        date = AirPowerUtil.getCurrentDateTime(),
                        listValue = 0.0,
                        budget = intBudget,
                        itemsCount = 0
                    )
                    mainViewModel.createList(newList)
                    navController.popBackStack()
                }
            )
        }
    )
}