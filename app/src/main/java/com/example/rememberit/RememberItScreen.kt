package com.example.rememberit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rememberit.ui.NoteViewModel

enum class RememberItScreen{
    Start,
    AboutApp,
    Note
}

@Composable
fun RememberItApp(
    noteViewModel: NoteViewModel,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = RememberItScreen.Start.name
    ){
        composable(route = RememberItScreen.Start.name) {
            NotesList(
                noteViewModel = noteViewModel,
                modifier = Modifier.fillMaxSize(),
                onNextButtonClicked = {
                    noteViewModel.updateId(0)
                    navController.navigate(RememberItScreen.Note.name)
                },
                aboutButtonClicked = {navController.navigate(RememberItScreen.AboutApp.name)},
                onCardClicked = {
                    noteViewModel.updateId(it)
                    navController.navigate(RememberItScreen.Note.name)
                }
            )
        }
        composable(route = RememberItScreen.AboutApp.name) {
            AboutRememberIt(
                modifier = Modifier.fillMaxSize(),
                onBackButtonClicked = {navController.navigate(RememberItScreen.Start.name)}
            )
        }
        composable(route = RememberItScreen.Note.name) {
            NoteFun(
                noteViewModel =noteViewModel,
                modifier = Modifier.fillMaxSize(),
                onBackButtonClicked = {
                    noteViewModel.updateId(0)
                    navController.navigate(RememberItScreen.Start.name)
                },
            )
        }
    }
}