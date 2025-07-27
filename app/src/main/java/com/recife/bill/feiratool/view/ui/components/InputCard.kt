package com.recife.bill.feiratool.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ifpe.edu.br.common.components.CustomCard
import com.ifpe.edu.br.common.ui.theme.cardCornerRadius


@Composable
fun CardDefault(
    layouts: List<@Composable () -> Unit>
) {
    CustomCard(
        paddingStart = 10.dp,
        paddingEnd = 10.dp,
        paddingTop = 5.dp,
        paddingBottom = 5.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(cardCornerRadius))
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.White),
        layouts = layouts
    )
}