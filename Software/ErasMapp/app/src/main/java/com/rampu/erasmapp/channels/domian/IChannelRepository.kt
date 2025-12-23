package com.rampu.erasmapp.channels.domian

import kotlinx.coroutines.flow.Flow

interface IChannelRepository {
    fun observeChannels(): Flow<ChannelSyncState>
    fun observeQuestions(channelId: String): Flow<QuestionsSyncState>
    fun observeSingleQuestion(channelId: String, questionId: String): Flow<QuestionDetailSyncState>
    suspend fun createChannel(
        title: String,
        topic: String,
        description: String? = null
    ): Result<Unit>

    suspend fun createQuestion(
        channelId: String,
        title: String,
        body: String
    ): Result<Unit>
}

sealed interface ChannelSyncState {
    data object Loading : ChannelSyncState
    data class Success(val channels: List<Channel>) : ChannelSyncState
    data class Error(val message: String) : ChannelSyncState
    data object SignedOut : ChannelSyncState
}

sealed interface QuestionsSyncState {
    data object Loading : QuestionsSyncState
    data class Success(val questions: List<Question>) : QuestionsSyncState
    data class Error(val message: String) : QuestionsSyncState
    data object SignedOut : QuestionsSyncState
}

sealed interface QuestionDetailSyncState {
    data object Loading : QuestionDetailSyncState
    data class Success(val question: Question) : QuestionDetailSyncState
    data class Error(val message: String) : QuestionDetailSyncState
    data object SignedOut: QuestionDetailSyncState
}