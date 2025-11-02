package com.rampu.erasmapp.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.rampu.erasmapp.auth.data.FirebaseAuthImplementation
import com.rampu.erasmapp.auth.domain.IAuthRepository
import com.rampu.erasmapp.auth.ui.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var appModule = module{
    single{ Firebase.auth }
    single<IAuthRepository> { FirebaseAuthImplementation(get()) }

    viewModel {
        AuthViewModel(get())
    }

}