package com.ifpe.edu.br.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: @Composable () -> Unit
) {
    override fun toString(): String {
        return "BottomNavItem(route='$route', label='$label', icon=$icon)"
    }
}