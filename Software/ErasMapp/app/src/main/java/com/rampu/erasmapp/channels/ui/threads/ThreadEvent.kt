package com.rampu.erasmapp.channels.ui.threads

sealed interface ThreadEvent {
    data object PostAnswer: ThreadEvent
    data class BodyChanged(val v : String) : ThreadEvent
}