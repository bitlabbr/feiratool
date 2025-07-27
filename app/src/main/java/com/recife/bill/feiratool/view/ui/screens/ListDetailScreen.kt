package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.components.CustomCard
import com.ifpe.edu.br.common.components.CustomColumn
import com.ifpe.edu.br.common.components.CustomText
import com.ifpe.edu.br.common.components.RectButton
import com.ifpe.edu.br.common.ui.theme.cardCornerRadius
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel


@Composable
fun ListDetailScreen(
    listId: String,
    navController: NavHostController,
    mainViewModel: AirPowerViewModel
) {

    LaunchedEffect(Unit) {
        mainViewModel.retrieveCurrentShoppList(listId)
    }

    val currentListFlow = mainViewModel.getCurrentShoppList().collectAsState()

    CustomColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        layouts = listOf {

            CustomCard(
                modifier = Modifier
                    .clip(RoundedCornerShape(cardCornerRadius))
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(Color.White),

                layouts = listOf {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        CustomText(
                            color = tb_primary_light,
                            text = "titulo",
                            fontSize = 20.sp
                        )
                    }

                    currentListFlow.value.entries.forEach { shoppEntryWithItem ->
                        ShopEntryCard(
                            shopEntry = shoppEntryWithItem.entry,
                            //shoppItem = ,
                            navController = navController
                        )
                    }

                    RectButton(
                        text = "Adicionar",
                        onClick = { navController.navigate(Screen.NewEntry.createRoute(listId)) }
                    )
                }
            )
        }
    )
}