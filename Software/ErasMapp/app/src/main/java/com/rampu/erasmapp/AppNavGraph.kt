package com.rampu.erasmapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rampu.erasmapp.auth.ui.LoginScreen

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = LoginRoute){
        composable<LoginRoute> {
            LoginScreen()
        }
    }
}