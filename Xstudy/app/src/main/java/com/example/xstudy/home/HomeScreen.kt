package com.example.xstudy.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xstudy.domain.model.DidYouKnow
import com.example.xstudy.domain.model.Subject
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


fun getRandomColor(): Color {
    return Subject.OtherCardColors.random()
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
