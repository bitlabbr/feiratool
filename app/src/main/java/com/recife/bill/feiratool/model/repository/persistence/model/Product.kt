package com.recife.bill.feiratool.model.repository.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "PRODUCT",
    indices = [Index(value = ["name"], unique = true)]
)
data class Product(
    @PrimaryKey
    @ColumnInfo(name = "PRODUCT_ID")
    val id: String,
    val name: String,
    val content: String? = null,
    val description: String? = null,
    val img: String? = null,
    val batch: String? = null,
    val exp: String? = null
)
{
    override fun toString(): String {
        return "Product(id='$id', name='$name', content=$content, description=$description, img=$img, batch=$batch, exp=$exp)"
    }
}