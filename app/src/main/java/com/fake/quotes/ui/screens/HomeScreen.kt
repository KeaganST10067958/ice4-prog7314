package com.fake.quotes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fake.quotes.ui.QuoteViewModel

@Composable
fun HomeScreen() {
    val vm: QuoteViewModel = viewModel()
    val state = vm.state.collectAsState().value

    LaunchedEffect(Unit) { vm.loadRandom() }

    Box(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                state.loading -> CircularProgressIndicator()
                state.error != null -> Text("Error: ${state.error}")
                state.fact != null -> {
                    Text(
                        text = "🐱 ${state.fact.fact}",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                }
                else -> Text("No data yet")
            }
            Spacer(Modifier.height(24.dp))
            Button(onClick = { vm.loadRandom() }) { Text("Next fact") }
        }
    }
}
