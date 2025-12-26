package com.rampu.erasmapp.channels.ui.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rampu.erasmapp.channels.domian.IChannelRepository
import com.rampu.erasmapp.channels.domian.QuestionsSyncState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionsViewModel(
    private val channelId: String,
    private val channelTitle: String,
    private val repo: IChannelRepository
) : ViewModel() {
    var uiState =
        MutableStateFlow(QuestionsUiState(channelId = channelId, channelTitle = channelTitle))
        private set
    private var observeJob: Job? = null

    init {
        observeQuestions()
    }

    private fun observeQuestions() {
        observeJob?.cancel()
        observeJob = viewModelScope.launch {
            repo.observeQuestions(channelId).collect { syncState ->
                when (syncState) {
                    is QuestionsSyncState.Loading -> uiState.update {
                        it.copy(
                            isLoading = true,
                            errorMsg = null
                        )
                    }
                    is QuestionsSyncState.Success -> uiState.update {
                        it.copy(
                            //TODO: Need to fix this to work with new QuestionListItem
                            questions = emptyList(),
                            isLoading = false,
                            errorMsg = null,
                            isSignedOut = false
                        )
                    }
                    is QuestionsSyncState.Error -> uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMsg = syncState.message,
                            isSignedOut = false
                        )
                    }
                    is QuestionsSyncState.SignedOut -> uiState.update {
                        it.copy(
                            questions = emptyList(),
                            isLoading = false,
                            errorMsg = "You need to sign in to view messages",
                            isSignedOut = true
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: QuestionsEvent){
        when(event){
            is QuestionsEvent.TitleChanged -> uiState.update { it.copy(newTitle = event.v) }
            is QuestionsEvent.BodyChanged -> uiState.update { it.copy(newBody = event.v) }
            is QuestionsEvent.CreateQuestion -> {
                createQuestion()
                uiState.update { it.copy(showCreateDialog = false) }
            }
            is QuestionsEvent.ShowCreateDialog -> uiState.update { it.copy(showCreateDialog = event.show) }
        }
    }

    fun createQuestion(){
        if(uiState.value.newTitle.isBlank()){
            uiState.update { it.copy(errorMsg = "Title is required") }
            return
        }

        viewModelScope.launch {
            val result = repo.createQuestion(
                channelId = channelId,
                title = uiState.value.newTitle,
                body = uiState.value.newBody
            )
            uiState.update {
                if(result.isSuccess){
                    it.copy(
                        newTitle = "",
                        newBody = "",
                        errorMsg = "",
                        isSaving = false,
                        resultMsg = "Question posted"
                    )
                }
                else{
                    it.copy(
                        isSaving = false,
                        resultMsg = "Posting failed. Try again."
                    )
                }
            }
        }
    }

}