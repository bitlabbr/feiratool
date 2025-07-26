package com.recife.bill.feiratool.model.repository.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.recife.bill.feiratool.model.repository.persistence.model.ShopItem
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppItemEntry
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppList
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppListWithEntries

@Dao
interface ShoppListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppList(shoppList: ShoppList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShopItem(shopItem: ShopItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppItemEntry(entry: ShoppItemEntry)


    @Transaction
    @Query("SELECT * FROM SHOPP_LIST WHERE LIST_ID = :listId")
    suspend fun getShoppListWithEntries(listId: String): ShoppListWithEntries?

    @Transaction
    @Query("SELECT * FROM SHOPP_LIST ORDER BY LIST_DATE DESC")
    suspend fun getAllShoppListsWithEntries(): List<ShoppListWithEntries>

    @Transaction
    @Query("SELECT * FROM SHOPP_LIST ORDER BY LIST_DATE DESC")
    suspend fun getAllShoppLists(): List<ShoppList>
}