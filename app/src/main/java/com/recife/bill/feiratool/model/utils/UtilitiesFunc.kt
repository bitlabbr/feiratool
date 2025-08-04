package com.recife.bill.feiratool.model.utils

import java.text.NumberFormat
import java.util.Locale

private val currencyFormatter by lazy {
    NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
}

fun Double.formatAsCurrencyBr(): String {
    return currencyFormatter.format(this)
}