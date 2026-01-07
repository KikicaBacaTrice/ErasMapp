package com.rampu.erasmapp.news.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rampu.erasmapp.news.domain.INewsRepository
import com.rampu.erasmapp.news.domain.NewsItem
import com.rampu.erasmapp.news.domain.NewsSyncState
import kotlinx.coroutines.flow.Flow

class FirebaseNewsRepository(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : INewsRepository {
    override fun observeNews(): Flow<NewsSyncState> {
        TODO("Not yet implemented")
    }

    override suspend fun createNews(item: NewsItem): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateNews(item: NewsItem): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(newsId: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}