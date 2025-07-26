package com.recife.bill.feiratool.model.repository.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "SHOPP_ENTRY_ITEM",
    primaryKeys = ["list_id", "item_id"],
    foreignKeys = [
        ForeignKey(
            entity = ShoppList::class,
            parentColumns = ["LIST_ID"],
            childColumns = ["list_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ShopItem::class,
            parentColumns = ["SHOPP_ITEM_ID"],
            childColumns = ["item_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("list_id"), Index("item_id")]
)
data class ShoppItemEntry(
    @ColumnInfo(name = "list_id")
    val listId: String,
    @ColumnInfo(name = "item_id")
    val itemId: String,
    val itemCount: Int,
    val itemsValue: Double
)