package com.recife.bill.feiratool.view.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.components.CustomCard
import com.ifpe.edu.br.common.components.CustomText
import com.recife.bill.feiratool.model.repository.persistence.model.ShopItem
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppItemEntry

@Composable
fun ShopEntryCard(
    shopEntry: ShoppItemEntry,
    //shoppItem: ShopItem,
    navController: NavHostController,
) {
    CustomCard(
        modifier = Modifier.clickable {

        },
        paddingStart = 15.dp,
        paddingEnd = 15.dp,
        paddingTop = 5.dp,
        paddingBottom = 5.dp,
        layouts = listOf {
           // CustomText(text = shoppItem.name)
            Row {
                CustomText(text = "R$:" + shopEntry.priceAtTimeOfEntry.toString(), color = Color.Black)
                CustomText(text = "*" + shopEntry.itemCount.toString(), color = Color.Black)
                CustomText(text = " = R$:" + shopEntry.itemsValue.toString(), color = Color.Black)
            }
        }
    )
}