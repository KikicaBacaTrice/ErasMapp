package com.rampu.erasmapp.channels.ui.threads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rampu.erasmapp.common.ui.components.LabeledInputField
import com.rampu.erasmapp.common.ui.components.LoadingIndicator

@Composable
fun ThreadScreen(
   // channelId: String,
    //channelTitle: String,
    //questionId: String,
    onBack: () -> Unit,
    onEvent: (event: ThreadEvent) -> Unit,
    state: ThreadUiState
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

        else -> {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(state.answers, key = { it.id }) { answer ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column {
                                Text(text = answer.authorLabel)
                                Text(text = answer.body)
                            }
                        }
                    }
                }
                Column{
                    LabeledInputField(
                        value = state.newAnswer,
                        onValueChange = {
                            onEvent(ThreadEvent.BodyChanged(it))
                        },
                        label = "Write your answer"
                    )
                    Button(
                        onClick = {
                            onEvent(ThreadEvent.PostAnswer)
                        }
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }

}