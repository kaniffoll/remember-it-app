package com.kaniffoll.rememberit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kaniffoll.rememberit.di.NoteListViewModelFactory
import com.kaniffoll.rememberit.di.NoteViewModelFactory
import com.kaniffoll.rememberit.presentation.ui.screens.AboutRememberItScreen
import com.kaniffoll.rememberit.presentation.ui.screens.list.NoteListViewModel
import com.kaniffoll.rememberit.presentation.ui.screens.list.NotesListScreen
import com.kaniffoll.rememberit.presentation.ui.screens.note.NoteScreen
import com.kaniffoll.rememberit.presentation.ui.screens.note.NoteViewModel

@Composable
fun Navigation(
    noteListFactory: NoteListViewModelFactory,
    noteFactory: NoteViewModelFactory.Factory,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.ListRoute
    ) {
        composable<NavRoute.ListRoute> {
            val viewModel: NoteListViewModel = viewModel(factory = noteListFactory)
            NotesListScreen(
                viewModel,
                onAboutClick = { navController.navigate(NavRoute.AboutRoute) },
                onCardClick = {
                    navController.navigate(NavRoute.NoteRoute(it)) {
                        popUpTo(NavRoute.ListRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<NavRoute.AboutRoute> {
            AboutRememberItScreen { navController.navigate(NavRoute.ListRoute) }
        }

        composable<NavRoute.NoteRoute> {
            val args = it.toRoute<NavRoute.NoteRoute>()
            val factoryWithId: NoteViewModelFactory = noteFactory.create(args.id)
            val viewModel: NoteViewModel = viewModel(factory = factoryWithId)
            NoteScreen(viewModel) {
                navController.navigate(NavRoute.ListRoute) {
                    popUpTo(NavRoute.ListRoute) { inclusive = true }
                }
            }
        }
    }
}