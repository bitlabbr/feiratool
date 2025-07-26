package com.recife.bill.feiratool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.recife.bill.feiratool.model.repository.Repository
import com.recife.bill.feiratool.view.manager.UIStateManager


class AirPowerViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val TAG: String = AirPowerViewModel::class.java.simpleName

    val uiStateManager = UIStateManager.getInstance()
    private var repository = Repository.getInstance()


    fun createList(listName: String) {
        repository.createList(listName)
    }

}