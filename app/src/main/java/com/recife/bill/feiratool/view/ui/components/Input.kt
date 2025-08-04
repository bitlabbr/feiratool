package com.recife.bill.feiratool.view.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ifpe.edu.br.common.components.CustomInputText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuantityInput(
    modifier: Modifier = Modifier,
    units: List<String> = listOf("Kg", "L", "Un"),
    onValueChange: (String, String) -> Unit
) {
    var value by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedUnit by remember { mutableStateOf(units.first()) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Campo de entrada numérica
        CustomInputText(
            value = value,
            onValueChange = {
                if (it.matches(Regex("^\\d*\\.?\\d*\$"))) {
                    value = it
                    onValueChange(value, selectedUnit)
                }
            },
            label = "Quantidade",
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            inputFieldColors = defaultInputTextColors()
        )

        Spacer(modifier = Modifier.width(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.width(150.dp)
        ) {
            CustomInputText(
                value = selectedUnit,
                onValueChange = {},
                readOnly = true,
                label = "Unidade",
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier.menuAnchor(),
                inputFieldColors = defaultInputTextColors()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                units.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(unit) },
                        onClick = {
                            selectedUnit = unit
                            expanded = false
                            onValueChange(value, selectedUnit)
                        }
                    )
                }
            }
        }
    }
}