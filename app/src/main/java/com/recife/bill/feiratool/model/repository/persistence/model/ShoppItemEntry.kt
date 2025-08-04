package com.recife.bill.feiratool.model.repository.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "SHOPP_ENTRY_ITEM",
    primaryKeys = ["list_id", "product_id"],
    foreignKeys = [
        ForeignKey(
            entity = ShoppList::class,
            parentColumns = ["LIST_ID"],
            childColumns = ["list_id"],
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = Product::class,
            parentColumns = ["PRODUCT_ID"],
            childColumns = ["product_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("list_id"), Index("product_id")]
)
data class ShoppItemEntry(
    @ColumnInfo(name = "list_id")
    val listId: String,
    @ColumnInfo(name = "product_id")
    val productId: String,
    val itemCount: Int,
    val itemsValue: Double,
    val priceAtTimeOfEntry: Double,
)
{
    override fun toString(): String {
        return "ShoppItemEntry(listId='$listId'," +
                "productId='$productId'," +
                "itemCount=$itemCount," +
                "itemsValue=$itemsValue," +
                "priceAtTimeOfEntry=$priceAtTimeOfEntry)"
    }
}