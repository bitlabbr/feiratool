package com.recife.bill.feiratool.model.repository.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SHOPP_ITEM")
data class ShopItem(
    @PrimaryKey
    @ColumnInfo(name = "SHOPP_ITEM_ID")
    val id: String,
    val name: String,
    val value: Double,
)