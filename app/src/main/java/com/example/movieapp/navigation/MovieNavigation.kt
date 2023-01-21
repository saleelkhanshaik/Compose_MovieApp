package com.example.movieapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.details.MovieDetailsScreen

private val TAG = "MovieNavigation"
@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreen.HomeScreen.name){
        composable(MovieScreen.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        //
        composable(MovieScreen.DetailScreen.name+"/{" +
                "movie}",
        arguments = listOf(navArgument(name = "movie"){
            type = NavType.StringType
        })
        ){
            it ->
            MovieDetailsScreen(navController = navController, it.arguments?.getString("movie"))
        }
    }

}