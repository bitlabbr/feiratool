package com.recife.bill.feiratool.model.repository

import android.content.Context
import com.recife.bill.feiratool.model.repository.persistence.AirPowerDatabase
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppList
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppListWithEntries
import com.recife.bill.feiratool.model.utils.AirPowerLog
import com.recife.bill.feiratool.model.utils.AirPowerUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class Repository private constructor(context: Context) {
    private val db = AirPowerDatabase.getDataBaseInstance(context)

    private val _shoppingLists = MutableStateFlow<List<ShoppListWithEntries>>(emptyList())
    val shoppingLists: StateFlow<List<ShoppListWithEntries>> get() = _shoppingLists


    companion object {
        @Volatile
        private var instance: Repository? = null
        fun build(context: Context) {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        if (AirPowerLog.ISLOGABLE)
                            AirPowerLog.d(TAG, "build()")
                        instance = Repository(context)
                    }
                }
            }
        }

        fun getInstance(): Repository {
            return instance
                ?: throw IllegalStateException("AirPowerRepository not initialized. Call build() first.")
        }

        private val TAG = Repository::class.simpleName


    }

    suspend fun loadAllShoppingLists() {
        val allLists = db.shoppListDao().getAllShoppListsWithEntries()
        _shoppingLists.value = allLists
    }

    suspend fun createList(listName: String) {
        val listId = UUID.randomUUID().toString()
        val novaLista = ShoppList(
            id = listId,
            name = listName,
            date = AirPowerUtil.getCurrentDateTime(),
            listValue = 0.0,
            itemsCount = 0
        )
        db.shoppListDao().insertShoppList(novaLista)
    }
}