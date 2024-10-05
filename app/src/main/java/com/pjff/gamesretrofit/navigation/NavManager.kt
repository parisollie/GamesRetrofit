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
        //Vid 229
        composable("DetailView/{id}/?{name}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            //Vid 229
            navArgument("name") { type = NavType.StringType },
        )  ){
            val id = it.arguments?.getInt("id") ?: 0
            //DetailView(viewModel, navController, id)
            //Vid 229
            val name = it.arguments?.getString("name") ?: ""
            DetailView(viewModel, navController, id, name)
        }
        //Vid 155
        composable("SearchGameView"){
           SearchGameView(viewModel, navController)
        }
    }
}