package com.rampu.erasmapp.foibuildings

import androidx.annotation.DrawableRes

data class Building(
    @DrawableRes val imageRes: Int, val id: Int,
    val description: String
)