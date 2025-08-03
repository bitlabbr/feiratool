package com.recife.bill.feiratool.view.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifpe.edu.br.common.components.CustomText
import com.recife.bill.feiratool.model.utils.formatAsCurrencyBr
import com.recife.bill.feiratool.view.ui.theme.tb_green_light
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.view.ui.theme.tb_primary_secondary

@Composable
fun ShoppingListSummary(totalValue: Double, budget: Double) {
    val remaining = budget - totalValue
    val remainingColor =
        if (remaining > 0) tb_green_light else tb_primary_secondary

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        // --- LINHA TOTAL DA COMPRA ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                text = "Total da Compra:",
                fontSize = 20.sp,
                color = Color.Black.copy(alpha = .8f)
            )
            CustomText(
                text = totalValue.formatAsCurrencyBr(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = tb_primary_secondary
            )
        }
        // --- LINHA DO ORÇAMENTO ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                text = "Orçamento:",
                fontSize = 20.sp,
                color = Color.Black.copy(alpha = .8f)
            )
            CustomText(
                text = budget.formatAsCurrencyBr(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = tb_primary_light
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        CustomDivider()
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        // --- LINHA SALDO ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                text = "Saldo:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black.copy(alpha = .8f)
            )
            CustomText(
                text = remaining.formatAsCurrencyBr(),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = remainingColor
            )
        }
    }
}