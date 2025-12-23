package com.rampu.erasmapp.channels.ui.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rampu.erasmapp.channels.domian.Question
import com.rampu.erasmapp.channels.ui.channels.ChannelEvent
import com.rampu.erasmapp.common.ui.components.LabeledInputField
import com.rampu.erasmapp.common.ui.components.LoadingIndicator

@Composable
fun QuestionsScreen(
    channelId: String,
    channelTitle: String,
    onBack: () -> Unit,
    onEvent: (event: QuestionsEvent) -> Unit,
    state: QuestionsUiState
) {
    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingIndicator()
            }
        }

        state.showCreateDialog -> {
            AlertDialog(
                onDismissRequest = {
                    onEvent(QuestionsEvent.ShowCreateDialog(false))
                },
                confirmButton = {
                    Button(
                        onClick = {
                            onEvent(QuestionsEvent.CreateQuestion)
                        }
                    ) {
                        Text("Post")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            onEvent(QuestionsEvent.ShowCreateDialog(false))
                        }
                    ) {
                        Text("Cancel")
                    }
                },
                text = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        LabeledInputField(
                            value = state.newTitle,
                            onValueChange = {
                                onEvent(QuestionsEvent.TitleChanged(it))
                            },
                            label = "Title"
                        )
                        LabeledInputField(
                            value = state.newBody,
                            onValueChange = {
                                onEvent(QuestionsEvent.BodyChanged(it))
                            },
                            label = "Topic"
                        )
                    }
                }
            )
        }

        else -> {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(state.questions, key = { it.id }) { question ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column() {
                                Text(question.title)
                                Text(question.body)
                            }

                        }
                    }

                }
                Button(
                    onClick = {
                        onEvent(QuestionsEvent.ShowCreateDialog(show = true))
                    }
                ) {
                    Text("Add question")
                }
            }
        }
    }


}