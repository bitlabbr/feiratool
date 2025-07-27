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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.recife.bill.feiratool.view.ui.theme.tb_primary_secondary

@Composable
fun ShoppingListSummary(totalValue: Double, budget: Double) {
    val remaining = budget - totalValue
    val remainingColor =
        if (remaining >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        // --- Valor Total ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total da Compra:",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "R$ ${"%.2f".format(totalValue)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = tb_primary_secondary
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        // --- Orçamento ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Orçamento:",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "R$ ${"%.2f".format(budget)}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        CustomDivider() // Usa o divisor que já criamos

        // --- Saldo Restante ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Saldo:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "R$ ${"%.2f".format(remaining)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = remainingColor // Cor muda se o saldo for negativo
            )
        }
    }
}