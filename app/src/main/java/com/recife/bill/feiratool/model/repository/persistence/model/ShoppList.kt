package com.recife.bill.feiratool.model.repository.persistence.model

//@Entity(tableName = "SHOPP_LIST")
data class ShoppList(
    //@PrimaryKey
    //@ColumnInfo(name = "LIST_ID")
    val id: String,
    //@ColumnInfo(name = "LIST_NAME")
    val name: String = "",
    //@ColumnInfo(name = "LIST_DATE")
    val date: String = "",

    val listValue: Double,

    val itemsCount: Int,

    val items: List<ShoppItemEntry>
)
