package com.recife.bill.feiratool.view.ui.screens

sealed class Screen(val route: String) {


    object Home : Screen("home") {
    }

    object Lists : Screen("lists") {
    }

    object Profile : Screen("profile") {
    }
}