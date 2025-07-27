package com.recife.bill.feiratool

import android.app.Application
import com.recife.bill.feiratool.model.repository.Repository
import com.recife.bill.feiratool.model.utils.AirPowerLog
import com.recife.bill.feiratool.viewmodel.AirPowerViewModelProvider

class App : Application() {
    private val tag = App::class.simpleName
    override fun onCreate() {
        if (AirPowerLog.ISLOGABLE) AirPowerLog.d(tag, "onCreate()")
        Repository.build(applicationContext)
        AirPowerViewModelProvider.getInstance(this)
        super.onCreate()
    }
}