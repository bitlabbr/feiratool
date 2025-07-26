package com.recife.bill.feiratool.model.repository.persistence.model

data class ShoppItemEntry(
    val id: String,
    val item: ShopItem,
    val itemCount: Int,
    val itemsValue: Double
)