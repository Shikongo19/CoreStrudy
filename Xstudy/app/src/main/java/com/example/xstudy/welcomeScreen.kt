package com.example.xstudy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.xstudy.authentication.BulletPoint
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WelcomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .horizontalScroll(state = rememberScrollState())
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Welcome to Xstudy!",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(horizontal = 26.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your personal study assistant",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.inverseSurface
        )
        Spacer(modifier = Modifier.height(10.dp))

        Image(
            modifier = Modifier
                .size(200.dp),
            painter = painterResource(R.drawable.bookmarks_pana),
            contentDescription = "")

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Xstudy helps you:",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column (
                modifier = Modifier.padding(5.dp),
            ){
                BulletPoint("Organize your study materials")
                BulletPoint("Create effective study schedules")
                BulletPoint("Track your progress")
                BulletPoint("Connect with study groups")
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Ready to boost your learning?",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.inverseSurface,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .width(250.dp)
        ) {
            Text("Get Started")
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}