package com.recife.bill.feiratool.model.repository.persistence.model

import androidx.room.Embedded
import androidx.room.Relation


data class ShoppEntryWithProduct(
    @Embedded
    val entry: ShoppItemEntry,

    @Relation(
        parentColumn = "product_id",
        entityColumn = "PRODUCT_ID"
    )
    val product: Product
)
