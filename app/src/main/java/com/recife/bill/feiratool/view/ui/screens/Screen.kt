package com.recife.bill.feiratool.view.ui.screens

sealed class Screen(val route: String) {


    object Home : Screen("home") {
    }

    object Lists : Screen("lists") {
    }

    object Profile : Screen("profile") {
    }

    data object NewList : Screen("newList")

    object ListDetail : Screen("list_detail_screen/{listId}") {
        fun createRoute(listId: String) = "list_detail_screen/$listId"
    }
}