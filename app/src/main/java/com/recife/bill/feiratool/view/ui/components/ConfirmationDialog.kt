package com.recife.bill.feiratool.view.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifpe.edu.br.common.components.CustomText
import com.recife.bill.feiratool.view.ui.theme.app_dialog_backgroud
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.view.ui.theme.tb_primary_secondary

@Composable
fun ConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector = Icons.Default.Warning
) {
    AlertDialog(
        icon = {
            Icon(
                icon,
                contentDescription = "Ícone de Alerta",
                tint = tb_primary_secondary,
                modifier = Modifier.size(50.dp)
            )
        },
        title = {
            CustomText(
                text = dialogTitle,
                alignment = TextAlign.Center,
                fontSize = 30.sp,
                maxLines = Int.MAX_VALUE,
                color = Color.Black.copy(alpha = .8f)
            )
        },
        text = {
            CustomText(
                text = dialogText,
                fontSize = 20.sp,
                minFontSize = 15.sp,
                alignment = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                maxLines = Int.MAX_VALUE,
                color = Color.Black.copy(alpha = .8f)
            )
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = "Confirmar", color = tb_primary_light)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = "Cancelar", color = tb_primary_secondary)
            }
        },
        containerColor = app_dialog_backgroud
    )
}