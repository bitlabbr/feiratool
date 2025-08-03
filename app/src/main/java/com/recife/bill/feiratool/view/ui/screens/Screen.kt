package com.recife.bill.feiratool.view.ui.screens

sealed class Screen(val route: String, val label: String = "") {
    data object Home : Screen("home", "Início")

    data object Lists : Screen("lists", "Listas")

    data object Profile : Screen("profile", "Perfil")

    data object NewList : Screen("newList", "Nova Lista")

    data object NewEntry : Screen("newEntry/{listId}", "Nova Entrada") {
        fun createRoute(listId: String) = "newEntry/$listId"
    }

    data object ListDetail : Screen("list_detail_screen/{listId}", "Detalhes") {
        fun createRoute(listId: String) = "list_detail_screen/$listId"
    }
}