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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.CommonConstants
import com.ifpe.edu.br.common.components.CustomColumn
import com.ifpe.edu.br.common.components.CustomText
import com.ifpe.edu.br.common.components.RectButton
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppItemEntry
import com.recife.bill.feiratool.view.ui.components.CardDefault
import com.recife.bill.feiratool.view.ui.components.ConfirmationDialog
import com.recife.bill.feiratool.view.ui.components.CustomDivider
import com.recife.bill.feiratool.view.ui.components.ShopEntryCard
import com.recife.bill.feiratool.view.ui.components.ShoppingListSummary
import com.recife.bill.feiratool.view.ui.components.SwipeToDeleteBackground
import com.recife.bill.feiratool.view.ui.components.defaultButtonColors
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
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
    var showDialog by remember { mutableStateOf(false) }
    var entryPendingDeletion by remember { mutableStateOf<ShoppItemEntry?>(null) }

    if (showDialog) {
        ConfirmationDialog(
            dialogTitle = "Confirmar Exclusão",
            dialogText = "Você realmente deseja remover este item da lista?",
            onDismissRequest = { showDialog = false },
            onConfirmation = {
                entryPendingDeletion?.let {
                    mainViewModel.deleteItemEntry(it)
                }
                showDialog = false
            }
        )
    }

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
                                // LINHA TITULO
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CustomText(
                                        text = details.shoppList.name,
                                        color = Color.Black,
                                        alignment = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 40.sp,
                                        minFontSize = 20.sp,
                                        maxLines = Int.MAX_VALUE,
                                        modifier = Modifier.padding(vertical = 24.dp)
                                    )
                                }

                                CustomDivider()
                            }

                            if (details.entries.isNotEmpty()) {
                                items(
                                    items = details.entries,
                                    key = { it.entry.itemId }
                                ) { shoppEntryWithItem ->

                                    val dismissState = rememberSwipeToDismissBoxState(
                                        confirmValueChange = {
                                            if (it == SwipeToDismissBoxValue.EndToStart) {
                                                entryPendingDeletion = shoppEntryWithItem.entry
                                                showDialog = true
                                                return@rememberSwipeToDismissBoxState false
                                            }
                                            return@rememberSwipeToDismissBoxState false
                                        }
                                    )

                                    SwipeToDismissBox(
                                        state = dismissState,
                                        enableDismissFromEndToStart = true,
                                        enableDismissFromStartToEnd = false,
                                        backgroundContent = {
                                            SwipeToDeleteBackground(dismissState = dismissState)
                                        }
                                    ) {
                                        ShopEntryCard(
                                            entryWithItem = shoppEntryWithItem,
                                            navController = navController
                                        )
                                    }
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
                                        colors = defaultButtonColors(),
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
            tint = tb_primary_light
        )
        Text(
            text = "Sua lista está vazia",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
        Text(
            text = "Clique em \"Adicionar\" para começar.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}