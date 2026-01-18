package com.rampu.erasmapp.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rampu.erasmapp.news.domain.INewsRepository
import com.rampu.erasmapp.news.domain.NewsSyncState
import com.rampu.erasmapp.user.domain.IUserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel(private val repo: INewsRepository, private val userRepo: IUserRepository) :
    ViewModel() {

    var uiState = MutableStateFlow(NewsUiState())
        private set

    private var observeJob: Job? = null

    init {
        observeNews()
    }

    private fun observeNews() {
        observeJob?.cancel()
        observeJob = viewModelScope.launch {
            repo.observeNews().collect { state ->
                when (state) {
                    is NewsSyncState.Loading -> uiState.update {
                        it.copy(
                            isLoading = true,
                            errorMsg = null
                        )
                    }

                    is NewsSyncState.Success -> {
                        uiState.update {
                            it.copy(
                                news = state.news,
                                isLoading = false,
                                errorMsg = null,
                                isSignedOut = false
                            )
                        }
                    }

                    is NewsSyncState.Error -> uiState.update {
                        it.copy(
                            isLoading = false,
                            isSignedOut = false,
                            errorMsg = state.message
                        )
                    }

                    is NewsSyncState.SignedOut -> uiState.update {
                        it.copy(
                            news = emptyList(),
                            isLoading = false,
                            errorMsg = "You need to sign in to view the news",
                            isSignedOut = true
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: NewsEvent) {

    }
}
