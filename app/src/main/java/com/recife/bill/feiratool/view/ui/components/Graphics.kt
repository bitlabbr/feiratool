package com.recife.bill.feiratool.view.ui.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.view.ui.theme.tb_primary_secondary


@Composable
fun CustomDivider() {
    Divider(
        modifier = Modifier.padding(vertical = 1.dp, horizontal = 16.dp),
        thickness = 1.dp,
        color = Color.Black.copy(alpha = 0.2f)
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

@Composable
fun SwipeToDeleteBackground(dismissState: SwipeToDismissBoxState) {
    val color = when (dismissState.targetValue) {
        SwipeToDismissBoxValue.Settled -> Color.Transparent
        SwipeToDismissBoxValue.StartToEnd -> tb_primary_secondary.copy(alpha = 0.8f)
        SwipeToDismissBoxValue.EndToStart -> tb_primary_secondary.copy(alpha = 0.8f)
    }

    val scale by animateFloatAsState(
        if (dismissState.targetValue == SwipeToDismissBoxValue.Settled) 0.75f else 1f, label = ""
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            Icons.Default.Delete,
            contentDescription = "Deletar Item",
            modifier = Modifier.scale(scale),
            tint = Color.White
        )
    }
}