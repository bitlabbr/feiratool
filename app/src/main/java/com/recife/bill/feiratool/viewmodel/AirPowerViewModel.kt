package com.recife.bill.feiratool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.recife.bill.feiratool.model.repository.Repository
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppListWithEntries
import com.recife.bill.feiratool.view.manager.UIStateManager
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AirPowerViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val TAG: String = AirPowerViewModel::class.java.simpleName

    val uiStateManager = UIStateManager.getInstance()
    private var repository = Repository.getInstance()


    fun createList(listName: String) {
        viewModelScope.launch {
            repository.createList(listName)
        }
    }

    fun loadAllShoppingLists() {
        viewModelScope.launch {
            repository.loadAllShoppingLists()
        }
    }

    fun getShoppLists(): StateFlow<List<ShoppListWithEntries>> {
        return repository.shoppingLists
    }

}