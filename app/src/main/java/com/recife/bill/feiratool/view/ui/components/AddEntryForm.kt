package com.recife.bill.feiratool.view.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.recife.bill.feiratool.model.repository.persistence.model.ShopItem
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppItemEntry
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel
import java.util.UUID

@Composable
fun AddEntryForm(
    mainViewModel: AirPowerViewModel,
    listId: String,
    navController: NavHostController
) {
    var newItemName by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Adicionar Novo Item", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = newItemName,
                onValueChange = { newItemName = it },
                label = { Text("Nome do Produto (Ex: Café Santa Clara)") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Qtd.") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                )
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Preço Unitário") },
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val priceDouble = price.replace(",", ".").toDoubleOrNull() ?: 0.0
                    val quantityInt = quantity.toIntOrNull() ?: 0

                    if (newItemName.isNotBlank() && priceDouble > 0 && quantityInt > 0) {
                        val newItemId = UUID.randomUUID().toString()
                        val newItem = ShopItem(
                            id = newItemId,
                            name = newItemName
                        )

                        val newShoppItemEntry = ShoppItemEntry(
                            listId = listId,
                            itemId = newItemId,
                            itemCount = quantityInt,
                            itemsValue = priceDouble * quantityInt,
                            priceAtTimeOfEntry = priceDouble
                        )

                        mainViewModel.addNewItemAndEntry(
                            shoppItem = newItem,
                            shoppItemEntry = newShoppItemEntry
                        )

                        newItemName = ""
                        quantity = ""
                        price = ""
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Adicionar à Lista")
            }
        }
    }
}