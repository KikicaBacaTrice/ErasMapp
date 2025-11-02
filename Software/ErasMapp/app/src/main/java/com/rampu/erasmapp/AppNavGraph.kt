package com.rampu.erasmapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rampu.erasmapp.auth.domain.UserAccount
import com.rampu.erasmapp.auth.ui.LoginScreen

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = LoginRoute,
    ){
        composable<LoginRoute> {
            val onLoginSuccess: (UserAccount) -> Unit = {user ->
                navController.navigate(TempHomeRoute(user.email ?: "none")){
                    popUpTo<LoginRoute> {inclusive = true}
                }
            }

            LoginScreen(
                onLoginSuccess = onLoginSuccess
            )
        }

        composable<TempHomeRoute>{ backStackEntry ->
            val route: TempHomeRoute = backStackEntry.toRoute()
            TempHome(route.email)
        }
    }
}