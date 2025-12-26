package com.rampu.erasmapp.channels.ui.threads

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rampu.erasmapp.channels.domian.Answer
import com.rampu.erasmapp.channels.domian.Question
import com.rampu.erasmapp.common.ui.components.LabeledInputField
import com.rampu.erasmapp.common.ui.components.LoadingIndicator
import com.rampu.erasmapp.common.ui.components.UserAvatar
import com.rampu.erasmapp.common.util.formatTime
import com.rampu.erasmapp.ui.theme.ErasMappTheme

@Composable
fun ThreadScreen(
    onBack: () -> Unit,
    onEvent: (event: ThreadEvent) -> Unit,
    state: ThreadUiState
) {
    val context = LocalContext.current

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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                    Text(text = state.channelTitle, style = MaterialTheme.typography.titleLarge)
                }

                state.question?.let { question ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        UserAvatar(label = question.authorLabel, photoUrl = question.authorPhotoUrl)
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = question.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = question.authorLabel,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                    RoundedCornerShape(10.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = formatTime(context, question.createdAt),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth(0.5f),
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f)
                        )
                        Spacer(Modifier.height(20.dp))
                        Text(
                            text = question.body,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.answers, key = { it.id }) { answer ->
                        AnswerBubble(
                            answer = answer,
                            isMine = answer.authorId == state.currentUserId,
                            timeText = formatTime(context, answer.createdAt)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    LabeledInputField(
                        value = state.newAnswer,
                        placeholder = "Your answer...",
                        onValueChange = { onEvent(ThreadEvent.BodyChanged(it)) },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    IconButton(
                        onClick = {onEvent(ThreadEvent.PostAnswer)},
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
                    }
                }
            }
        }
    }
}

@Composable
fun AnswerBubble(answer: Answer, isMine: Boolean, timeText: String) {
    val color =
        if (isMine) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isMine) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!isMine) {
            UserAvatar(label = answer.authorLabel, photoUrl = null, size = 36.dp)
            Spacer(Modifier.width(8.dp))
        }

        Column(
            modifier = Modifier
                .background(color, RoundedCornerShape(12.dp))
                .padding(12.dp)
                .fillMaxWidth(0.85f)
        ) {
            Text(
                text = answer.authorLabel, style = MaterialTheme.typography.labelSmall
            )
            Text(text = answer.body, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = timeText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ThreadScreenPreview() {
    ErasMappTheme {
        ThreadScreen(
            onBack = {},
            onEvent = {},
            state = ThreadUiState(
                channelId = "channelId",
                channelTitle = "Channel title",
                questionId = "questionId",
                question = Question(
                    id = "questionId",
                    channelId = "channelId",
                    title = "Title",
                    body = "Question body text here",
                    authorId = "authorId",
                    authorLabel = "Author name",
                    authorPhotoUrl = null,
                    createdAt = System.currentTimeMillis() - 2 * DateUtils.DAY_IN_MILLIS,
                    lastActivityAt = System.currentTimeMillis(),
                    lastMessagePreview = "Last message preview",
                    answerCount = 1
                ),
                answers = listOf(
                    Answer(
                        id = "answerId",
                        channelId = "channelId",
                        questionId = "questionId",
                        body = "This is a preview answer",
                        authorId = "authorId",
                        authorLabel = "Author Name",
                        createdAt = System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS
                    )
                ),
                isLoading = false,
                errorMsg = null,
                isSignedOut = false,
                isSaving = false,
                newAnswer = "",
                currentUserId = "userId",
                canSendAnswer = true
            )
        )
    }
}