package com.recife.bill.feiratool.model.repository

import android.content.Context
import com.recife.bill.feiratool.model.utils.AirPowerLog

class Repository private constructor(context: Context) {
    //private val db = AirPowerDatabase.getDataBaseInstance(context)

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
}