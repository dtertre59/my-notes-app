package com.example.mynotesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mynotesapp.repositories.NoteRepository
import com.example.mynotesapp.ui.DetailsScreen
import com.example.mynotesapp.ui.HomeScreen
import com.example.mynotesapp.viewModels.DetailsViewModel
import com.example.mynotesapp.viewModels.HomeViewModel

@Composable
fun NotesNavHost(
    noteRepository: NoteRepository,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val homeViewModel = remember { HomeViewModel(repository = noteRepository) }

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable(route = "home") {
            HomeScreen(
                onEditNote = { noteId ->
                    print("Note ID: $noteId")
                    navController.navigate("details/$noteId")
                             }
                , homeViewModel)
        }
        composable(route = "details/{noteId}",
            arguments = listOf(navArgument("noteId"){type = NavType.StringType})
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: ""
            val detailsViewModel = remember { DetailsViewModel(noteId, noteRepository) }
            DetailsScreen(
                onBack = {
                    // navController.navigate(route = "home")
                    navController.popBackStack()
                         },
                onDelete = {
                    // navController.navigate(route = "home")
                    navController.popBackStack()
                },
                detailsViewModel= detailsViewModel)
        }
    }
}
