package com.recife.bill.feiratool.view.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.components.CustomCard
import com.ifpe.edu.br.common.components.CustomColumn
import com.ifpe.edu.br.common.components.CustomText
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppListWithEntries
import com.recife.bill.feiratool.model.utils.formatAsCurrencyBr
import com.recife.bill.feiratool.view.ui.screens.Screen
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.view.ui.theme.tb_primary_secondary

@Composable
fun ShoppListCard(
    navController: NavHostController,
    item: ShoppListWithEntries,
) {
    CustomCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate(Screen.ListDetail.createRoute(item.shoppList.id))
            },
        layouts = listOf {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TitleRow(item)
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                BudgedRow(item)
                TotaRow(item)
                ItemsCountRow(item)
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                DateRow(item)
            }
        }
    )
}

@Composable
private fun DateRow(item: ShoppListWithEntries) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CustomText(
            text = item.shoppList.date,
            fontSize = 18.sp,
            color = Color.Black.copy(alpha = .5f)
        )
    }
}

@Composable
private fun ItemsCountRow(item: ShoppListWithEntries) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomText(
            text = "Itens: ",
            fontSize = 20.sp,
            color = Color.Black.copy(alpha = .6f)
        )
        CustomText(
            text = "${item.shoppList.itemsCount}",
            fontSize = 25.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun TotaRow(item: ShoppListWithEntries) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val listValueFormated = item.shoppList.listValue.formatAsCurrencyBr()
        if (listValueFormated.length > 15) {
            CustomColumn(
                layouts = listOf {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            text = "Total: ",
                            fontSize = 20.sp,
                            color = Color.Black.copy(alpha = .6f)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            text = listValueFormated,
                            fontSize = 30.sp,
                            overflow = TextOverflow.Visible,
                            fontWeight = FontWeight.Bold,
                            color = tb_primary_secondary
                        )
                    }
                }
            )
        } else {
            CustomText(
                text = "Total: ",
                fontSize = 20.sp,
                color = Color.Black.copy(alpha = .6f)
            )
            CustomText(
                text = listValueFormated,
                fontSize = 30.sp,
                overflow = TextOverflow.Visible,
                fontWeight = FontWeight.Bold,
                color = tb_primary_secondary
            )
        }
    }
}

@Composable
private fun BudgedRow(item: ShoppListWithEntries) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val budgetFormatted = item.shoppList.budget.formatAsCurrencyBr()
        if (budgetFormatted.length > 10) {
            CustomColumn(
                layouts = listOf {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            text = "Orçamento: ",
                            fontSize = 20.sp,
                            color = Color.Black.copy(alpha = .6f),
                            maxLines = 1
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(
                            text = budgetFormatted,
                            fontSize = 25.sp,
                            overflow = TextOverflow.Visible,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            color = tb_primary_light
                        )
                    }
                }
            )
        } else {
            CustomText(
                text = "Orçamento: ",
                fontSize = 20.sp,
                color = Color.Black.copy(alpha = .6f),
                maxLines = 1
            )
            CustomText(
                text = budgetFormatted,
                fontSize = 25.sp,
                overflow = TextOverflow.Visible,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = tb_primary_light
            )
        }
    }
}

@Composable
private fun TitleRow(item: ShoppListWithEntries) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomText(
            text = item.shoppList.name,
            color = Color.Black,
            alignment = TextAlign.Justify,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            minFontSize = 30.sp,
            maxLines = Int.MAX_VALUE,
        )
    }
}