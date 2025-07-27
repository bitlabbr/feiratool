package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.components.CustomInputText
import com.ifpe.edu.br.common.components.RectButton
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppList
import com.recife.bill.feiratool.model.utils.AirPowerUtil
import com.recife.bill.feiratool.view.ui.components.CardDefault
import com.recife.bill.feiratool.view.ui.components.CustomDivider
import com.recife.bill.feiratool.view.ui.components.defaultInputTextColors
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.view.ui.theme.tb_primary_secondary
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel

@Composable
fun NewListScreen(
    navController: NavHostController,
    mainViewModel: AirPowerViewModel
) {
    var listName by rememberSaveable { mutableStateOf("") }
    var budget by rememberSaveable { mutableStateOf("") }
    var isNameError by rememberSaveable { mutableStateOf(false) }

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
                        text = "Criar lista",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = tb_primary_light
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 15.dp))

                CustomDivider()

                // --- CAMPO NOME DA LISTA ---
                CustomInputText(
                    inputFieldColors = defaultInputTextColors(),
                    value = listName,
                    onValueChange = {
                        listName = it
                        isNameError = it.isBlank()
                    },
                    label = "Nome da lista",
                    placeholder = "Ex: Compras do Mês",
                    isError = isNameError
                )

                if (isNameError) {
                    Text(
                        text = "O nome é obrigatório",
                        color = tb_primary_secondary,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                CustomInputText(
                    inputFieldColors = defaultInputTextColors(),
                    value = budget,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*([.,])?\\d*\$"))) {
                            budget = newValue
                        }
                    },
                    label = "Orçamento da lista",
                    placeholder = "0,00",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.padding(vertical = 30.dp))

                // --- BOTÃO CRIAR LISTA ---
                RectButton(
                    text = "Criar Lista",
                    modifier = Modifier.fillMaxWidth(),
                    enabled = listName.isNotBlank(),
                    onClick = {
                        val budgetDouble = budget.replace(',', '.').toDoubleOrNull() ?: 0.0
                        val newList = ShoppList(
                            id = java.util.UUID.randomUUID().toString(),
                            name = listName,
                            date = AirPowerUtil.getCurrentDateTime(),
                            listValue = 0.0,
                            budget = budgetDouble,
                            itemsCount = 0
                        )
                        mainViewModel.createList(newList)
                        navController.popBackStack()
                    }
                )

                Spacer(modifier = Modifier.padding(vertical = 15.dp))
            }
        )
    }
}