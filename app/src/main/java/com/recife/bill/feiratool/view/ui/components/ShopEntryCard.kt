package com.recife.bill.feiratool.view.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.components.CustomCard
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppEntryWithItem
import com.recife.bill.feiratool.model.utils.formatAsCurrencyBr
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light

@Composable
fun ShopEntryCard(
    entryWithItem: ShoppEntryWithItem,
    navController: NavHostController,
) {
    CustomCard(
        modifier = Modifier.padding(vertical = 4.dp),
        paddingStart = 16.dp,
        paddingEnd = 16.dp,
        paddingTop = 12.dp,
        paddingBottom = 12.dp,
        layouts = listOf {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ItemLabelRow(entryWithItem)
                ItemsCountValueRow(entryWithItem)
                ItemsSumRow(entryWithItem)
            }
        }
    )
}

@Composable
private fun ItemsSumRow(entryWithItem: ShoppEntryWithItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {

        Text(
            text = buildAnnotatedString {
                append("Subtotal: ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = tb_primary_light
                    )
                ) {
                    append(entryWithItem.entry.itemsValue.formatAsCurrencyBr())
                }
            },
            fontSize = 14.sp,
            color = Color.Black.copy(alpha = .8f)
        )
    }
}

@Composable
private fun ItemsCountValueRow(entryWithItem: ShoppEntryWithItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "${entryWithItem.entry.itemCount} x ${entryWithItem.entry.priceAtTimeOfEntry.formatAsCurrencyBr()}",
            fontSize = 14.sp,
            color = Color.Black.copy(alpha = .8f),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun ItemLabelRow(entryWithItem: ShoppEntryWithItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(entryWithItem.shopItem.name)
                }
            },
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}