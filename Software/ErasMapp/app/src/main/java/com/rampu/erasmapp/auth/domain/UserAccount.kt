package com.rampu.erasmapp.auth.domain

data class UserAccount (
    val uid: String  = "",
    val email: String? = null,
    val name: String? = null
)