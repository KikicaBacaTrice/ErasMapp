package com.rampu.erasmapp.auth.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rampu.erasmapp.R
import com.rampu.erasmapp.common.ui.LayoutTestPreview
import com.rampu.erasmapp.common.ui.components.LabeledInputField
import com.rampu.erasmapp.common.ui.components.Logo
import com.rampu.erasmapp.ui.theme.ErasMappTheme

@Composable
fun LoginScreen(
    state: LoginUiState,
    onEvent: (event: LoginEvent) -> Unit,
    onNavigateToRegister: () -> Unit
){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Logo(
                modifier = Modifier
                    .size(150.dp)
            )

            Spacer(Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.sign_in_to_your_account),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(Modifier.height(30.dp))

            LabeledInputField(
                value = state.email,
                onValueChange = {
                    onEvent(LoginEvent.EmailChanged(it))
                },
                label = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth(0.8f),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = stringResource(R.string.email)
                    )

                },
                placeholder = stringResource(R.string.email),
                isError = state.emailError != null,
                supportingText = {
                    if(state.emailError != null) Text(text = state.emailError)
                }
            )

            Spacer(Modifier.height(10.dp))

            LabeledInputField(
                value = state.password,
                onValueChange = {
                    onEvent(LoginEvent.PasswordChanged(it))
                },
                label = stringResource(R.string.password),
                modifier = Modifier.fillMaxWidth(0.8f),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = stringResource(R.string.password)
                    )

                },
                placeholder = stringResource(R.string.password),
                isError = state.passwordError != null,
                supportingText = {
                    if(state.passwordError != null) Text(text = state.passwordError)
                }
            )

            Spacer(Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f),

                onClick = {
                    onEvent(LoginEvent.Submit)
                },
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(vertical = 15.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Text(
                    text = stringResource(R.string.sign_in),
                )
            }

            Spacer(Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                verticalAlignment = Alignment.CenterVertically,
            ){
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                )
                Text(
                    text = "OR",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                )
            }

            Spacer(Modifier.height(30.dp))

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(0.8f),

                onClick = {
                    //TODO: add google login processing
                },
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(vertical = 15.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        painter = painterResource(R.drawable.google_logo),
                        contentDescription = stringResource(R.string.sign_in_with_google),
                        tint = Color.Unspecified,
                        modifier = Modifier.requiredSize(20.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = stringResource(R.string.sign_in_with_google),
                    )
                }

            }



        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don’t have an account?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            TextButton(onClick = onNavigateToRegister) {
                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@LayoutTestPreview
@Composable
fun LoginScreenPreview(){
        ErasMappTheme {
            LoginScreen(
                state = LoginUiState(),
                onEvent = {}
            ) { }
        }



}