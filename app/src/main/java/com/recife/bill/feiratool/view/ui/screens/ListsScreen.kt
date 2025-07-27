package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.CommonConstants
import com.ifpe.edu.br.common.components.CustomColumn
import com.ifpe.edu.br.common.components.RectButton
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppList
import com.recife.bill.feiratool.view.ui.components.*
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel

@Composable
fun ListsScreen(
    navController: NavHostController,
    mainViewModel: AirPowerViewModel
) {
    LaunchedEffect(Unit) {
        mainViewModel.loadAllShoppingLists()
    }

    val shopListState by mainViewModel.getShoppLists().collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var listPendingDeletion by remember { mutableStateOf<ShoppList?>(null) }

    if (showDialog) {
        ConfirmationDialog(
            dialogTitle = "Confirmar Exclusão",
            dialogText = "Tem certeza que deseja excluir a lista \"" +
                    "${listPendingDeletion?.name}\"?\n\nEsta ação não pode ser desfeita.",
            onDismissRequest = { showDialog = false },
            onConfirmation = {
                listPendingDeletion?.let {
                    mainViewModel.deleteShoppList(it)
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
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        contentPadding = PaddingValues(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (shopListState.isEmpty()) {
                            item {
                                EmptyStateMessage()
                            }
                        } else {
                            items(
                                items = shopListState,
                                key = { it.shoppList.id }
                            ) { item ->
                                val dismissState = rememberSwipeToDismissBoxState(
                                    confirmValueChange = {
                                        if (it == SwipeToDismissBoxValue.EndToStart) {
                                            listPendingDeletion = item.shoppList
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
                                    ShoppListCard(
                                        navController = navController,
                                        item = item
                                    )
                                }
                                CustomDivider()
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.padding(vertical = 25.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RectButton(
                                    text = "Nova Lista",
                                    onClick = { navController.navigate(Screen.NewList.route) }
                                )
                            }
                            Spacer(modifier = Modifier.padding(vertical = 10.dp))
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
            text = "Você não tem listas ainda",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
        Text(
            text = "Clique em \"Nova Lista\" para começar.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}