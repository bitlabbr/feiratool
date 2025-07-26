package com.recife.bill.feiratool.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.recife.bill.feiratool.model.utils.AirPowerLog

object AirPowerViewModelProvider {
    private val tag = AirPowerViewModelProvider::class.simpleName
    private var singletonViewModel: AirPowerViewModel? = null
    fun getInstance(
        application: Application,
    ): AirPowerViewModel {
        if (singletonViewModel == null) {
            if (AirPowerLog.ISLOGABLE)
                AirPowerLog.d(tag, "$tag -> create instance")
            val factory = AirPowerViewModelFactory(application)
            singletonViewModel = ViewModelProvider(
                ViewModelStore(),
                factory
            )[AirPowerViewModel::class.java]
        }
        return singletonViewModel!!
    }

    fun getInstance(): AirPowerViewModel {
        if (singletonViewModel == null) {
            val errorMessage =
                "${AirPowerViewModel::class.simpleName} build error: getInstance called before construction"
            throw IllegalStateException(errorMessage)
        }
        return singletonViewModel!!
    }
}