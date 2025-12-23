package com.rampu.erasmapp.channels.ui.threads

import com.rampu.erasmapp.channels.domian.Question

data class ThreadUiState(
    val channelId: String,
    val channelTitle: String,
    val questionId: String,
)
