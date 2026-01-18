package com.rampu.erasmapp.news.ui

import com.rampu.erasmapp.news.domain.NewsItem

data class NewsUiState(
    val news: List<NewsItem> = emptyList(),
    val isLoading: Boolean = true,
    val errorMsg: String? = null,
    val isSignedOut: Boolean = false,
)
