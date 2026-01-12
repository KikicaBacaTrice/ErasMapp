package com.rampu.erasmapp.foibuildings

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rampu.erasmapp.R


private val buildings = listOf(
    Building(R.drawable.foi1, 1, "This is the main FOI building."),
    Building(R.drawable.foi2, 2, "This is the second FOI building."),
    Building(R.drawable.foi3, 3, "This is the third FOI building.")
)

@Composable
fun BuildingScreen(
    onBack: () -> Unit
) {
    var selectedBuilding by remember { mutableStateOf<Int?>(null) }

    BackHandler(enabled = selectedBuilding != null) {
        selectedBuilding = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        buildings.forEach { building ->
            AnimatedVisibility(
                visible = selectedBuilding == null || selectedBuilding == building.id,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier.clickable {
                        selectedBuilding = if (selectedBuilding == building.id) null else building.id
                    },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = building.imageRes),
                        contentDescription = "FOI ${building.id}"
                    )
                    AnimatedVisibility(
                        visible = selectedBuilding == building.id,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Text(
                            text = building.description,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
        IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
    }
}
