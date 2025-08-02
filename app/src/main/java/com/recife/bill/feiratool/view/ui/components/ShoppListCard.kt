package com.recife.bill.feiratool.view.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.components.CustomCard
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppListWithEntries
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
                // --- LINHA DO TÍTULO ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.shoppList.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                // --- LINHA DO ORÇAMENTO ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Orçamento: ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black.copy(alpha = .8f)
                    )
                    Text(
                        text = "R$ ${"%.2f".format(item.shoppList.budget)}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = tb_primary_light
                    )
                }
                // --- LINHA DE ITENS ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Itens: ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black.copy(alpha = .8f)
                    )
                    Text(
                        text = "${item.shoppList.itemsCount}",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                // --- LINHA DO TOTAL ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black.copy(alpha = .8f)
                    )
                    Text(
                        text = "R$ ${"%.2f".format(item.shoppList.listValue)}",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium,
                        color = tb_primary_secondary
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                // --- LINHA DA DATA ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = item.shoppList.date,
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = .8f)
                    )
                }
            }
        }
    )
}