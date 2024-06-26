package com.example.xstudy.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun ReturningUserScreen(
    username: String,
    onContinue: () -> Unit
) {
    val (wordOfTheDay, tip) = remember { getRandomWordAndTip() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        WelcomeBack(username)
        WordOfTheDay(wordOfTheDay)
        DailyTip(tip)
        Spacer(modifier = Modifier.weight(1f))
        ContinueButton(onContinue)
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun WelcomeBack(username: String) {
    Text(
        text = "Welcome back, $username!",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = "Ready for another productive study session?",
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun WordOfTheDay(word: Pair<String, String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Word of the Day",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = word.first,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = word.second,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun DailyTip(tip: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Study Tip",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tip,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ContinueButton(onContinue: () -> Unit) {
    Button(
        onClick = onContinue,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Continue to Study Session")
    }
}

// This function simulates fetching a random word and tip.
// In a real app, you'd probably fetch this from a database or API.
fun getRandomWordAndTip(): Pair<Pair<String, String>, String> {
    val words = listOf(
        "Ephemeral" to "Lasting for a very short time.",
        "Ubiquitous" to "Present, appearing, or found everywhere.",
        "Serendipity" to "The occurrence of events by chance in a happy or beneficial way.",
        "Eloquent" to "Fluent or persuasive in speaking or writing."
    )

    val tips = listOf(
        "Try the Pomodoro Technique: Study for 25 minutes, then take a 5-minute break.",
        "Teach what you've learned to someone else to reinforce your understanding.",
        "Create mind maps to visualize connections between different concepts.",
        "Review your notes within 24 hours of taking them to improve retention."
    )

    return Pair(words[Random.nextInt(words.size)], tips[Random.nextInt(tips.size)])
}