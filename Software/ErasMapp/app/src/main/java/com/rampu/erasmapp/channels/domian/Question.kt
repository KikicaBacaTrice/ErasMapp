package com.rampu.erasmapp.channels.domian

import com.google.firestore.v1.Value

data class Question(
    val id: String,
    val channelId: String,
    val title: String,
    val body: String,
    val authorId: String,
    val authorLabel: String,
    val authorPhotoUrl: String? = null,
    val createdAt: Long,
    val lastActivityAt: Long = createdAt,
    val lastMessagePreview: String = "",
    val answerCount: Long = 0L,
    val status: QuestionsStatus = QuestionsStatus.OPEN,
    val acceptedAnswerId: String? = null
)

enum class QuestionsStatus(val firestoreValue: String){
    OPEN("open"),
    ANSWERED("answered"),
    LOCKED("locked");

    companion object{
        fun fromFirestore(value: String?): QuestionsStatus = when(value?.lowercase()){
            ANSWERED.firestoreValue -> ANSWERED
            LOCKED.firestoreValue -> LOCKED
            else -> OPEN
        }
    }
}
