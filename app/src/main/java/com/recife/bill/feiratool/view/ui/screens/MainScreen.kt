package com.recife.bill.feiratool.view.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.ifpe.edu.br.common.CommonConstants
import com.ifpe.edu.br.common.components.BottomNavItem
import com.ifpe.edu.br.common.components.CustomColumn
import com.ifpe.edu.br.common.components.CustomNavigationBar
import com.ifpe.edu.br.common.components.CustomText
import com.ifpe.edu.br.common.components.CustomTopBar
import com.ifpe.edu.br.common.components.GradientBackground
import com.recife.bill.feiratool.view.ui.theme.appBackgroundGradientDark
import com.recife.bill.feiratool.view.ui.theme.appBackgroundGradientLight
import com.recife.bill.feiratool.view.ui.theme.tb_primary_light
import com.recife.bill.feiratool.viewmodel.AirPowerViewModel
import java.util.UUID

@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: AirPowerViewModel,
    componentActivity: ComponentActivity
) {
    val TAG = "MainScreen"

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val screensWithBottomBar = listOf(
        Screen.Home.route,
        Screen.Lists.route,
        Screen.Profile.route
    )

    val context = LocalContext.current
    val shouldShowBottomBar = currentRoute in screensWithBottomBar

    CustomColumn(
        alignmentStrategy = CommonConstants.Ui.ALIGNMENT_TOP,
        layouts = listOf {
            Scaffold(
                topBar = {
                    val title = when (currentRoute) {
                        Screen.Home.route -> "Início"
                        Screen.Lists.route -> "Listas"
                        Screen.Profile.route -> "Perfil"
                        else -> ""
                    }

                    CustomTopBar(
                        backgroundColor = Color.Transparent,
                        centerContent = {
                            CustomText(
                                text = title,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Medium,
                                color = tb_primary_light,
                                alignment = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    )
                },

                bottomBar = {
                    if (shouldShowBottomBar) {
                        CustomNavigationBar(
                            backgroundColor = Color.Transparent,
                            navController = navController,
                            items = listOf(
                                BottomNavItem.Home,
                                BottomNavItem.Devices,
                                BottomNavItem.DashBoards
                            )
                        )
                    }
                }
            ) { innerPadding ->
                GradientBackground(
                    if (isSystemInDarkTheme()) appBackgroundGradientDark
                    else appBackgroundGradientLight
                )
                NavHostContainer(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                    mainViewModel = mainViewModel
                )
            }
        }
    )
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    mainViewModel: AirPowerViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                HomeScreen(navController, mainViewModel)
            }
        }
        composable(Screen.Lists.route) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                ListsScreen(navController, mainViewModel)
            }
        }
        composable(Screen.Profile.route) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                ProfileScreen(navController, mainViewModel)
            }
        }

        composable(Screen.NewList.route) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                NewListScreen(navController, mainViewModel)
            }
        }

        composable(
            route = Screen.ListDetail.route,
            arguments = listOf(navArgument("listId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val result = runCatching {
                val listIdString = backStackEntry.arguments?.getString("listId")
                val listUuid = UUID.fromString(listIdString)
                ListDetailScreen(
                    listId = listUuid,
                    navController = navController,
                    mainViewModel = mainViewModel
                )
            }
            if (!result.isSuccess) {
                navController.popBackStack()
            }
        }
    }
}