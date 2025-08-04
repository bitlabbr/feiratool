package com.recife.bill.feiratool.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ifpe.edu.br.common.R
import com.ifpe.edu.br.common.components.CustomCard
import com.ifpe.edu.br.common.components.CustomColumn
import com.ifpe.edu.br.common.components.CustomInputText
import com.ifpe.edu.br.common.components.CustomText
import com.ifpe.edu.br.common.components.RectButton
import com.ifpe.edu.br.common.components.RoundedImageIcon
import com.ifpe.edu.br.common.ui.theme.cardCornerRadius
import com.recife.bill.feiratool.model.repository.persistence.model.Product
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppItemEntry
import com.recife.bill.feiratool.view.ui.theme.cardBackgroundGradientLightVariant
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel
import java.util.UUID

@Composable
fun AddEntryForm(
    mainViewModel: AirPowerViewModel,
    listId: String,
    navController: NavHostController
) {

    LaunchedEffect(Unit) {
        mainViewModel.loadAllProducts()
    }

    val productCatalog by mainViewModel.getAllProducts().collectAsState()

    var productName by rememberSaveable { mutableStateOf("") }
    var productContentValue by rememberSaveable { mutableStateOf("") }
    var productContentUnit by rememberSaveable { mutableStateOf("") }

    var quantity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val filteredProducts = productCatalog.filter {
        it.name.contains(productName, ignoreCase = true)
    }

    CustomColumn(
        layouts = listOf {
            Spacer(modifier = Modifier.height(15.dp))
            CustomCard(
                paddingBottom = 10.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(cardCornerRadius))
                    .background(
                        brush = Brush.linearGradient(
                            colors = cardBackgroundGradientLightVariant,
                            start = Offset(0f, 0f),
                            end = Offset(1000f, 1000f)
                        )
                    ),
                layouts = listOf {
                    Spacer(modifier = Modifier.padding(vertical = 5.dp))
                    TitleRow()
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    ImageAndNameRow(
                        isDropdownExpanded,
                        productName,
                        selectedProduct,
                        filteredProducts
                    )
                    Spacer(modifier = Modifier.padding(vertical = 5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        QuantityInput { quantity, unit ->
                        }
                    }
                    Spacer(modifier = Modifier.padding(vertical = 5.dp))
                }
            )
        }
    )

    Column {
        Spacer(modifier = Modifier.height(15.dp))
        Row(Modifier.fillMaxWidth()) {
            CustomInputText(
                inputFieldColors = defaultInputTextColors(),
                modifier = Modifier
                    .weight(1f),
                value = quantity,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        quantity = newValue
                    }
                },
                label = "Qtd.",
                textAlign = TextAlign.Center,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                iconColor = tb_primary_light
            )

            CustomInputText(
                inputFieldColors = defaultInputTextColors(),
                modifier = Modifier
                    .weight(2f),
                value = price,
                onValueChange = { newValue ->
                    if (newValue.isEmpty() || newValue.matches(Regex("^\\d*([.,])?\\d*\$"))) {
                        price = newValue
                    }
                },
                label = "Preço Unitário",
                placeholder = "0,00",
                textAlign = TextAlign.End,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                iconColor = tb_primary_light
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        RectButton(
            text = "Adicionar à Lista",
            enabled = productName.isNotBlank() && quantity.isNotBlank() && price.isNotBlank(),
            onClick = {
                val priceDouble = price.replace(",", ".").toDoubleOrNull() ?: 0.0
                val quantityInt = quantity.toIntOrNull() ?: 0
                val productToSave = selectedProduct ?: Product(
                    id = UUID.randomUUID().toString(),
                    name = productName
                )
                val newShoppItemEntry = ShoppItemEntry(
                    listId = listId,
                    productId = productToSave.id,
                    itemCount = quantityInt,
                    itemsValue = priceDouble * quantityInt,
                    priceAtTimeOfEntry = priceDouble
                )
                mainViewModel.addNewItemAndEntry(
                    product = productToSave,
                    shoppItemEntry = newShoppItemEntry
                )
                navController.popBackStack()
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ImageAndNameRow(
    isDropdownExpanded: Boolean,
    productName: String,
    selectedProduct: Product?,
    filteredProducts: List<Product>
) {
    var isDropdownExpanded1 = isDropdownExpanded
    var productName1 = productName
    var selectedProduct1 = selectedProduct
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedImageIcon(
            description = "",
            modifier = Modifier.size(80.dp),
            iconResId = R.drawable.generic_error
        )
        ExposedDropdownMenuBox(
            expanded = isDropdownExpanded1,
            onExpandedChange = { isDropdownExpanded1 = !isDropdownExpanded1 }
        ) {
            CustomInputText(
                modifier = Modifier.menuAnchor(),
                value = productName1,
                onValueChange = {
                    productName1 = it
                    isDropdownExpanded1 = true
                    selectedProduct1 = null
                },
                label = "Nome do Produto",
                placeholder = "Digite para buscar ou criar",
                inputFieldColors = defaultInputTextColors()
            )

            ExposedDropdownMenu(
                expanded = isDropdownExpanded1,
                onDismissRequest = { isDropdownExpanded1 = false }
            ) {
                filteredProducts.forEach { product ->
                    DropdownMenuItem(
                        text = { Text(product.name) },
                        onClick = {
                            selectedProduct1 = product
                            productName1 = product.name
                            isDropdownExpanded1 = false
                        }
                    )
                }
                if (productName1.isNotBlank() && filteredProducts.none {
                        it.name.equals(
                            productName1,
                            ignoreCase = true
                        )
                    }) {
                    DropdownMenuItem(
                        text = { Text("Criar novo produto: \"$productName1\"") },
                        onClick = {
                            selectedProduct1 =
                                null // Garante que ta criando um novo
                            isDropdownExpanded1 = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun TitleRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomText(
            text = "Detalhe do produto",
            fontSize = 30.sp,
            color = Color.Black
        )
    }
}