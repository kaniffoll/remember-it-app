package com.kaniffoll.rememberit.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kaniffoll.rememberit.presentation.ui.screens.AboutRememberItScreen
import com.kaniffoll.rememberit.presentation.ui.screens.note.NoteScreen
import com.kaniffoll.rememberit.presentation.ui.screens.list.NotesListScreen

@Composable
fun RememberItApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.ListRoute
    ) {
        composable<NavRoute.ListRoute> {
            NotesListScreen(
                onAboutClick = { navController.navigate(NavRoute.AboutRoute) },
                onCardClick = {
                    navController.navigate(navController.navigate(NavRoute.NoteRoute)) {
                        popUpTo(NavRoute.ListRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<NavRoute.AboutRoute> {
            AboutRememberItScreen { navController.navigate(NavRoute.ListRoute) }
        }

        composable<NavRoute.NoteRoute> {
            NoteScreen {
                navController.navigate(NavRoute.ListRoute) {
                    popUpTo(NavRoute.ListRoute) { inclusive = true }
                }
            }
        }
    }
}