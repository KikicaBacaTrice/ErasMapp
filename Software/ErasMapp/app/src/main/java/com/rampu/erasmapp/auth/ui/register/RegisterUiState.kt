package com.rampu.erasmapp.auth.ui.register

import com.rampu.erasmapp.auth.validators.FieldError

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,

    val isLoading: Boolean = false,
    val error: String? = null
)
