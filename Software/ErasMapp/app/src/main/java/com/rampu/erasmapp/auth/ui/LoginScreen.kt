package com.rampu.erasmapp.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rampu.erasmapp.auth.domain.UserAccount
import com.rampu.erasmapp.ui.theme.ErasMappTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    vm: AuthViewModel = koinViewModel<AuthViewModel>(),
    onLoginSuccess: (user: UserAccount) -> Unit
){

    val state by vm.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.loggedInAs) {
        state.loggedInAs?.let{ user ->
            onLoginSuccess(user)
        }
    }


    ErasMappTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hello",
                    style = MaterialTheme.typography.headlineMedium
                )
                OutlinedTextField(
                    value = state.email,
                    onValueChange = vm::onEmailChange,
                    singleLine = true,
                    label = {
                        Text(
                            text = "Email"
                        )
                    }
                )

                OutlinedTextField(
                    value = state.password,
                    onValueChange = vm::onPasswordChange,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = {
                        Text(
                            text = "Password"
                        )
                    }
                )
                
                Button(
                    onClick = vm::signIn
                ){
                    Text("Sign In")
                }
            }

        }
    }


}