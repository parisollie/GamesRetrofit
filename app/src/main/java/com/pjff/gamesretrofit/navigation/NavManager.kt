package com.pjff.gamesretrofit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pjff.gamesretrofit.viewModel.GamesViewModel
import com.pjff.gamesretrofit.views.DetailView
import com.pjff.gamesretrofit.views.HomeView
import com.pjff.gamesretrofit.views.SearchGameView

//Vid 148,
@Composable
fun NavManager(viewModel: GamesViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(viewModel, navController)
        }
        composable("DetailView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )  ){
            val id = it.arguments?.getInt("id") ?: 0
            DetailView(viewModel, navController, id)
        }
        //Vid 155
        composable("SearchGameView"){
           SearchGameView(viewModel, navController)
        }
    }
}