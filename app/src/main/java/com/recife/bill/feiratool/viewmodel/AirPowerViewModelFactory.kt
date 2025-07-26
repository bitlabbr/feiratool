package com.recife.bill.feiratool.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AirPowerViewModelFactory(
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirPowerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AirPowerViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}