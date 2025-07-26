package com.recife.bill.feiratool.model.repository.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SHOPP_LIST")
data class ShoppList(
    @PrimaryKey
    @ColumnInfo(name = "LIST_ID")
    val id: String,
    @ColumnInfo(name = "LIST_NAME")
    val name: String = "",
    @ColumnInfo(name = "LIST_DATE")
    val date: String = "",
    val listValue: Double,
    val itemsCount: Int,
)