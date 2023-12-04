package com.sameh.news.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(newsVM: NewsVM, sharedVM: SharedVM) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.NewsScreen.route) {
        composable(route = Screen.NewsScreen.route) {
            NewsScreen(navController = navController, newsVM, sharedVM)
        }
        composable(route = Screen.ArticleScreen.route) {
            ArticleScreen(navController = navController, sharedVM = sharedVM)
        }
    }
}