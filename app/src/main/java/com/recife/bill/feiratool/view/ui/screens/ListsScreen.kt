package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.CommonConstants
import com.ifpe.edu.br.common.components.CustomColumn
import com.ifpe.edu.br.common.components.CustomText
import com.ifpe.edu.br.common.components.RectButton
import com.recife.bill.feiratool.view.ui.components.CardDefault
import com.recife.bill.feiratool.view.ui.components.CustomDivider
import com.recife.bill.feiratool.view.ui.components.ShoppListCard
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

    val scrollState = rememberScrollState()
    val shopListFlow = mainViewModel.getShoppLists().collectAsState()

    CustomColumn(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
        alignmentStrategy = CommonConstants.Ui.ALIGNMENT_TOP,
        layouts = listOf {
            CardDefault(
                layouts = listOf{

                    if (shopListFlow.value.isEmpty()) {
                        Spacer(modifier = Modifier.padding(vertical = 40.dp))
                        EmptyStateMessage()
                        Spacer(modifier = Modifier.padding(vertical = 20.dp))
                    } else {
                        shopListFlow.value.forEach {
                                item ->
                            ShoppListCard(
                                navController,
                                item
                            )
                            CustomDivider()
                        }
                    }

                    Spacer(modifier = Modifier.padding(vertical = 15.dp))

                    RectButton(
                        text = "Nova Lista",
                        onClick = { navController.navigate(Screen.NewList.route) }
                    )
                    Spacer(modifier = Modifier.padding(vertical = 15.dp))
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
            text = "Você não tem listas ainda",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Clique em \"Nova Lista\" para começar.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}