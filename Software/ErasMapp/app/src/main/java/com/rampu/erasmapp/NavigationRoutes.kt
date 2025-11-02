package com.rampu.erasmapp

import androidx.annotation.StyleRes
import com.rampu.erasmapp.auth.domain.UserAccount
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Serializable
object RegisterRoute

@Serializable
data class TempHomeRoute(val email: String)