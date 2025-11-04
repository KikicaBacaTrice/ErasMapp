package com.rampu.erasmapp.auth.validators

import android.util.Patterns

object Validators {
    fun validateEmail(email: String): FieldError? {
        val trimmed = email.trim()
        if(trimmed.isEmpty()) return FieldError.Empty

        val isCorrectFormat = Patterns.EMAIL_ADDRESS.matcher(trimmed).matches()
        if(!isCorrectFormat) return FieldError.InvalidEmail
        return null
    }

    private val passwordHasLetters = Regex("[A-Za-z]")
    private val passwordHasNumbers = Regex("\\d")

    fun validatePassword(password: String): FieldError? {
        if(password.isEmpty()) return FieldError.Empty
        else if(password.length < 8) return FieldError.WeakPassword
        else if (!passwordHasLetters.containsMatchIn(password) || !passwordHasNumbers.containsMatchIn(password))
            return FieldError.WeakPassword

        return null
    }

    fun validateConfirmPassword(password: String, confirmedPassword: String): FieldError? {
        if(confirmedPassword.isEmpty()) return FieldError.Empty
        if(password != confirmedPassword) return FieldError.InvalidConfirmPassword
        return null
    }
}

sealed class FieldError(val msg: String){
    data object Empty : FieldError("Field is required")
    data object InvalidEmail : FieldError("Email format is invalid.")
    data object WeakPassword : FieldError("Password is too weak.")
    data object InvalidConfirmPassword: FieldError("Passwords do not match.")
}