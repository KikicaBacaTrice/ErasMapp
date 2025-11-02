package com.rampu.erasmapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rampu.erasmapp.auth.domain.UserAccount
import com.rampu.erasmapp.auth.ui.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TempHome(email: String){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Hello $email")

        val vm = koinViewModel<AuthViewModel>()

        Button(
            onClick =vm:: signOut
        ){
            Text("Sign out")
        }
    }

}