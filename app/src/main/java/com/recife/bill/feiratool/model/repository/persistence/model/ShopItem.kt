package com.recife.bill.feiratool.model.repository.persistence.model

//@Entity(tableName = "SHOPP_ITEM")
data class ShopItem(
    //@PrimaryKey
    //@ColumnInfo(name = "SHOPP_ITEM_ID")
    val id: String,
    val name: String,
    val value: Double,
)