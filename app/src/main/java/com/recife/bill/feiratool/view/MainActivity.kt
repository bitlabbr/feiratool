package com.recife.bill.feiratool.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.recife.bill.feiratool.R
import com.recife.bill.feiratool.model.utils.AirPowerLog
import com.recife.bill.feiratool.view.ui.screens.MainScreen
import com.recife.bill.feiratool.view.ui.theme.FeiraToolTheme
import com.recife.bill.feiratool.viewmodel.AirPowerViewModelProvider

class MainActivity : ComponentActivity() {
    val TAG = MainActivity::class.simpleName
    private val viewModel = AirPowerViewModelProvider.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        if (AirPowerLog.ISLOGABLE) AirPowerLog.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            FeiraToolTheme {
                MainScreen(
                    navController = navController,
                    mainViewModel = viewModel,
                    componentActivity = this
                )
            }
        }
    }
}