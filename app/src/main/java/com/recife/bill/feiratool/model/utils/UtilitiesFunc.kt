package com.recife.bill.feiratool.model.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.formatAsCurrencyBr(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(this)
}