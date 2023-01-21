package com.example.movieapp.navigation


enum class MovieScreen {
    HomeScreen,
    DetailScreen;

    companion object{
        fun fromRoute(route:String?):MovieScreen =
            when(route?.substringBefore("/")){
                HomeScreen.name -> HomeScreen
                DetailScreen.name -> DetailScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}