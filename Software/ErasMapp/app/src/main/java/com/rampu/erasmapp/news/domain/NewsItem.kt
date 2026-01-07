package com.rampu.erasmapp.news.domain

data class NewsItem(
    val id: String,
    val title: String,
    val body: String,
    val topic: String,
    val isUrgent: String,
    val createdAt: Long,
    val publishedAt: Long,
    val expiresAt: Long?,
    val authorId: String?
)
