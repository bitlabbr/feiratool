package com.recife.bill.feiratool.model.repository.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.recife.bill.feiratool.model.repository.persistence.model.Product
import com.recife.bill.feiratool.model.repository.persistence.model.ShopItem
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppItemEntry
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppList
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppListWithEntries

@Dao
interface ShoppListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppList(shoppList: ShoppList)

    @Update
    suspend fun updateShoppList(shoppList: ShoppList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppItemEntry(entry: ShoppItemEntry)

    @Transaction
    @Query("SELECT * FROM SHOPP_LIST WHERE LIST_ID = :listId")
    suspend fun getShoppListWithEntriesByListId(listId: String): ShoppListWithEntries?

    @Transaction
    @Query("SELECT * FROM SHOPP_LIST ORDER BY LIST_DATE DESC")
    suspend fun getAllShoppListsWithEntries(): List<ShoppListWithEntries>

    @Query("SELECT * FROM PRODUCT ORDER BY name ASC")
    suspend fun getAllProducts(): List<Product>

    @Delete
    suspend fun deleteShoppItemEntry(entry: ShoppItemEntry)

    @Delete
    suspend fun deleteShoppList(shoppList: ShoppList)
}