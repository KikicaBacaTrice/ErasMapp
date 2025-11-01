package com.rampu.erasmapp.auth.ui

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rampu.erasmapp.auth.domain.AuthResult
import com.rampu.erasmapp.auth.domain.IAuthRepository
import com.rampu.erasmapp.auth.domain.UserAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val error: String? = null,
    val loggedInAs: UserAccount? = null
)

class AuthViewModel (private val authRepository: IAuthRepository) : ViewModel() {
    var state = MutableStateFlow(AuthUiState())
        private set

    init{
        viewModelScope.launch {
            authRepository.authState.collect { account ->
                state.update { it.copy(loggedInAs = account, loading = false) }
            }
        }
    }

    fun onEmailChange(value: String) = state.update { it.copy(email = value, error = null)  }
    fun onPasswordChange(value: String) = state.update { it.copy(password = value, error = null) }

    fun signIn() = viewModelScope.launch {
        val email = state.value.email
        val password = state.value.password

        state.update { it.copy(loading = true, error = null) }
        when(val result = authRepository.signIn(email = email, password = password)) {
            is AuthResult.Success -> state.update { it.copy(loading = false, loggedInAs = result.user, error = null) }
            is AuthResult.Failure -> state.update { it.copy(loading = false, error = result.message) }
        }

    }

    fun register() = viewModelScope.launch {
        val email = state.value.email
        val password = state.value.password

        state.update { it.copy(loading = true, error = null) }
        when(val result = authRepository.register(email = email, password = password)) {
            is AuthResult.Success -> state.update { it.copy(loading = false, loggedInAs = result.user, error = null) }
            is AuthResult.Failure -> state.update { it.copy(loading = false, error = result.message) }
        }
    }

    fun signOut() = viewModelScope.launch {
        state.update { it.copy(loading = true ) }
        authRepository.signOut()
        state.update { it.copy(loading = false, loggedInAs = null) }
    }
}

