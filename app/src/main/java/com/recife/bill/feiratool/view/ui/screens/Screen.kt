package com.recife.bill.feiratool.view.ui.screens

sealed class Screen(val route: String) {


    data object Home : Screen("home")

    data object Lists : Screen("lists")

    data object Profile : Screen("profile")

    data object NewList : Screen("newList")

    data object NewEntry : Screen("newEntry/{listId}") {
        fun createRoute(listId: String) = "newEntry/$listId"
    }

    data object ListDetail : Screen("list_detail_screen/{listId}") {
        fun createRoute(listId: String) = "list_detail_screen/$listId"
    }
}