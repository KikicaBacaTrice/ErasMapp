package com.rampu.erasmapp.channels.domian

import kotlinx.coroutines.flow.Flow

interface IChannelRepository {
    fun observeChannels(): Flow<ChannelSyncState>
    fun observeQuestions(channelId: String) : Flow<QuestionsSyncState>
    suspend fun createChannel(
        title: String,
        topic: String,
        description: String? = null
    ) : Result<Unit>
    suspend fun createQuestion(
        channelId: String,
        title: String,
        body: String
    ): Result<Unit>
}

sealed interface ChannelSyncState{
    data object Loading: ChannelSyncState
    data class Success(val channels: List<Channel>): ChannelSyncState
    data class Error (val message: String) : ChannelSyncState
    data object SignedOut: ChannelSyncState
}

sealed interface QuestionsSyncState{
    data object Loading: QuestionsSyncState
    data class Success(val questions: List<Question>): QuestionsSyncState
    data class Error(val message: String) : QuestionsSyncState
    data object  SignedOut: QuestionsSyncState
}