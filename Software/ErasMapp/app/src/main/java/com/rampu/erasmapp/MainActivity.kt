package com.rampu.erasmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.rampu.erasmapp.auth.data.FirebaseAuthImplementation
import com.rampu.erasmapp.auth.ui.AuthScreen
import com.rampu.erasmapp.auth.ui.AuthViewModel
import com.rampu.erasmapp.ui.theme.ErasMappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authViewModel = remember { AuthViewModel(authRepository = FirebaseAuthImplementation()) }
            
            ErasMappTheme {
                AuthScreen(
                    vm = authViewModel
                )
            }
        }
    }
}
