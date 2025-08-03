package com.ifpe.edu.br.common.components

/*
* Trabalho de conclusão de curso - IFPE 2025
* Author: Willian Santos
* Project: AirPower Costumer
*/

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import kotlin.math.max

@Composable
fun TextTitle(
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    message: String,
    fontSize: TextUnit = 20.sp,
    modifier: Modifier = Modifier
) {
    Text(
        text = message,
        fontWeight = FontWeight.Bold,
        color = textColor,
        fontSize = fontSize,
        textAlign = textAlign,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun CustomInputText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false,
    singleLine: Boolean = true,
    shape: Shape = RectangleShape,
    inputFieldColors: TextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
        focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
        focusedContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
    ),
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textAlign: TextAlign = TextAlign.Start,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = label?.let { { Text(it) } },
        placeholder = { Text(placeholder, color = iconColor) },
        singleLine = singleLine,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,

        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else keyboardOptions,
        textStyle = TextStyle(textAlign = textAlign),
        isError = isError,
        supportingText = supportingText,

        leadingIcon = leadingIcon,
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        tint = iconColor
                    )
                }
            }
        } else null,
        shape = shape,
        colors = inputFieldColors
    )
}

@Composable
fun CustomText(
    text: String,
    alignment: TextAlign = TextAlign.Left,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    fontSize: TextUnit = 16.sp,
    minFontSize: TextUnit = 12.sp,
    threshold: Int = 10,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    fontWeight: FontWeight = FontWeight.Bold,
    modifier: Modifier = Modifier
        .wrapContentWidth()
        .padding(start = 4.dp, end = 4.dp)
) {
    val adjustedSize = remember(text) {
        val length = text.length
        if (length <= threshold) fontSize
        else {
            val excess = length - threshold
            val reduced = fontSize.value - excess
            max(minFontSize.value, reduced).sp
        }
    }
    Text(
        textAlign = alignment,
        text = text,
        color = color,
        fontSize = adjustedSize,
        fontWeight = fontWeight,
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow
    )
}