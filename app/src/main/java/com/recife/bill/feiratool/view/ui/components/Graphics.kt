package com.recife.bill.feiratool.view.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light


@Composable
fun CustomDivider() {
    Divider(
        modifier = Modifier.padding(vertical = 1.dp, horizontal = 16.dp),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
    )
}

@Composable
fun defaultInputTextColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedTextColor = tb_primary_light,
        unfocusedTextColor = tb_primary_light,
        focusedLabelColor = tb_primary_light,
        unfocusedLabelColor = tb_primary_light,
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White
    )
}

@Composable
fun defaultButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = tb_primary_light,
        contentColor = Color.White,
        disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
        disabledContentColor = Color.White.copy(alpha = 0.7f)
    )
}