package com.recife.bill.feiratool.model.repository.persistence.model

import androidx.room.Embedded
import androidx.room.Relation

data class ShoppListWithEntries(
    @Embedded
    val shoppList: ShoppList,

    @Relation(
        entity = ShoppItemEntry::class,
        parentColumn = "LIST_ID",
        entityColumn = "list_id"
    )
    val entries: List<ShoppEntryWithProduct>
)