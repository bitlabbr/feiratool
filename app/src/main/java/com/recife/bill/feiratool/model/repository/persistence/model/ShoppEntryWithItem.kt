package com.recife.bill.feiratool.model.repository.persistence.model

import androidx.room.Embedded
import androidx.room.Relation


data class ShoppEntryWithItem(
    @Embedded
    val entry: ShoppItemEntry,

    @Relation(
        parentColumn = "item_id",
        entityColumn = "SHOPP_ITEM_ID"
    )
    val shopItem: ShopItem
)
