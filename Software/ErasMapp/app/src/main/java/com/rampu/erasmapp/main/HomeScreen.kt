package com.rampu.erasmapp.main

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rampu.erasmapp.NavigationActivity
import com.rampu.erasmapp.eventCalendar.data.EventCalendarRepository
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    onSignOut: () -> Unit,
    onGoToSchedule: () -> Unit,
    onGoToEventCalendar: () -> Unit,
    onGoToAdmin: () -> Unit
){
    val repository: EventCalendarRepository = koinInject()
    val adminFlow = remember(repository) { repository.observeAdminStatus() }
    val isAdmin by adminFlow.collectAsState(initial = false)
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Home screen")

        Spacer(modifier = Modifier.height(20.dp))

        
        Button(onClick = {
            context.startActivity(Intent(context, NavigationActivity::class.java))
        }) {
            Text("Navigation")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onSignOut
        ){
            Text("Sign out")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onGoToSchedule) {
            Text("Go to Schedule")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onGoToEventCalendar) {
            Text("Go to Event Calendar")
        }

        if (isAdmin) {
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onGoToAdmin) {
                Text("Admin console")
            }
        }
    }

}
