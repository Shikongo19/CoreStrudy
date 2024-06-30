package com.example.xstudy.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xstudy.domain.model.DidYouKnow
import com.example.xstudy.domain.model.MotivationQuote
import com.example.xstudy.domain.model.Subject
import com.example.xstudy.subjects
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    firstName: String,
    lastName: String,
    subjects: List<String>,
    onLogout: () -> Unit,
    onProfileClick: () -> Unit,
    onCardClick: (String) -> Unit,
    onSubjectClick: (String) -> Unit,
    motivationalQuotes: List<MotivationQuote>,
) {

    Scaffold(
        topBar = { HomeTopBar(firstName, lastName, onLogout, onProfileClick) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { WelcomeText(firstName) }

            item { SubjectsList(subjects, onSubjectClick) }
            item { ActionCards(onCardClick) }
            item { ActivityCards(onCardClick) }
            item { LearningSubjects(onSubjectClick) }
        }
    }
}

fun getRandomColor(): Color {
    return Subject.OtherCardColors.random()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    firstName: String,
    lastName: String,
    onLogout: () -> Unit,
    onProfileClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(onClick = onProfileClick)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("$firstName ${lastName}.")
            }
        },
        actions = {
            IconButton(onClick = onLogout) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
            }
        }
    )
}

@Composable
fun WelcomeText(firstName: String) {
    Column (
        modifier = Modifier
            .padding(start = 16.dp)
    ){
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "Welcome back, $firstName!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "Welcome back!",
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(26.dp))
    }
}

@Composable
fun titleSmall(title: String)
{
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.W700,
        color = MaterialTheme.colorScheme.primary
    )
}
@Composable
fun MotivationalQuote(quotes: List<DidYouKnow>) {
    val defaultQuote = DidYouKnow(0, "Did you know?", "")
    var currentQuote by remember { mutableStateOf(quotes.firstOrNull() ?: defaultQuote) }
    var backgroundColor by remember { mutableStateOf(getRandomColor()) }
    var progress by remember { mutableStateOf(0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 8000),
        label = "Progress"
    )

    LaunchedEffect(Unit) {
        while (true) {
            progress = 1f
            delay(15.seconds)
            currentQuote = quotes.random()
            backgroundColor = getRandomColor()
            progress = 0f
        }
    }
    Row {
        titleSmall(title = "Did you know ?")
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
                .verticalScroll(state = rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = currentQuote.body,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

        }

    }
}

@Composable
fun SubjectsList(subjects: List<String>, onSubjectClick: (String) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(subjects) { subject ->
            ElevatedButton(
                modifier = Modifier.padding(start = 10.dp),onClick = { onSubjectClick(subject) }) {
                Text(subject)
            }
        }
    }
}

@Composable
fun ActionCards(onCardClick: (String) -> Unit) {
    val actions = listOf("Start Studying", "Take Note", "Drawing", "Chatting", "Instagram")

    Column {
        Text(
            text = "Quick Actions",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            items(actions) { action ->
                ActionCard(action, onCardClick)
            }
        }
    }
}

@Composable
fun ActionCard(action: String, onClick: (String) -> Unit) {
    val backgroundColor = remember { getRandomColor() }

    Card(
        modifier = Modifier
            .size(120.dp)
            .padding(start = 16.dp)
            .clickable { onClick(action) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1B556C)),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column (
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = action,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}



@Composable
fun ActivityCards(onCardClick: (String) -> Unit) {
    val activities = listOf("Math Quiz", "Critical Thinking Quiz", "Science Quiz", "History Quiz")

    Column {
        Text(
            text = "Activities",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 16.dp)
        ) {
            items(activities) { activity ->
                ActionCard(activity, onCardClick)
            }
        }
    }
}

@Composable
fun LearningSubjects(onSubjectClick: (String) -> Unit) {
    val subjects = listOf(
        "Learn Java", "Learn HTML", "Learn CSS", "Learn JavaScript",
        "Learn Math", "Learn Namibian History", "Learn Physics", "Learn Biology"
    )

    Column {
        Text(
            text = "Subjects to Learn",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(subjects) { subject ->
                ActionCard(subject, onSubjectClick)
            }
        }
    }
}

// Usage
