package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.CommonConstants
import com.ifpe.edu.br.common.components.CustomColumn
import com.ifpe.edu.br.common.components.RectButton
import com.recife.bill.feiratool.view.ui.components.CardDefault
import com.recife.bill.feiratool.view.ui.components.CustomDivider
import com.recife.bill.feiratool.view.ui.components.ShopEntryCard
import com.recife.bill.feiratool.view.ui.components.ShoppingListSummary
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel

@Composable
fun ListDetailScreen(
    listId: String,
    navController: NavHostController,
    mainViewModel: AirPowerViewModel
) {
    LaunchedEffect(key1 = listId) {
        mainViewModel.retrieveCurrentShoppList(listId)
    }

    val currentListState by mainViewModel.getCurrentShoppList().collectAsState()
    CustomColumn(
        modifier = Modifier
            .fillMaxSize(),
        alignmentStrategy = CommonConstants.Ui.ALIGNMENT_CENTER,
        layouts = listOf {
            CardDefault(
                layouts = listOf {
                    currentListState.let { details ->
                        LazyColumn(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                        ) {
                            item {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = details.shoppList.name,
                                        style = MaterialTheme.typography.headlineMedium,
                                        modifier = Modifier.padding(vertical = 24.dp)
                                    )
                                }

                                CustomDivider()
                            }

                            if (details.entries.isNotEmpty()) {
                                items(details.entries) { shoppEntryWithItem ->
                                    ShopEntryCard(
                                        entryWithItem = shoppEntryWithItem,
                                        navController = navController
                                    )
                                    CustomDivider()
                                }

                                item {
                                    Spacer(modifier = Modifier.padding(vertical = 30.dp))
                                    ShoppingListSummary(
                                        totalValue = details.shoppList.listValue,
                                        budget = details.shoppList.budget
                                    )
                                    Spacer(modifier = Modifier.padding(vertical = 15.dp))
                                }

                            } else {
                                item {
                                    EmptyStateMessage()
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RectButton(
                                        text = "Adicionar Item",
                                        onClick = {
                                            navController.navigate(
                                                Screen.NewEntry.createRoute(
                                                    listId
                                                )
                                            )
                                        },
                                    )
                                }
                                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                            }
                        }
                    }
                }
            )
        }
    )
}

@Composable
private fun EmptyStateMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Lista Vazia",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        )
        Text(
            text = "Sua lista está vazia",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Clique em \"Adicionar\" para começar.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}