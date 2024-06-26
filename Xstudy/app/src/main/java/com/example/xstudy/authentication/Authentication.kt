package com.example.xstudy.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.xstudy.R
import com.example.xstudy.loader.ScreenLoader
import com.example.xstudy.repositories.AppViewModel
import kotlinx.coroutines.delay

@Composable
fun authentication(appViewModel: AppViewModel): Boolean {
    val isLoading by appViewModel.isLoading.collectAsState()
    val next by rememberSaveable { mutableStateOf(false) }

    val authenticated by appViewModel.authenticated.collectAsState()
    val isLogin by appViewModel.isLogin.collectAsState()

    WelcomeScreen(
        onGetStarted = {}
    )

    if (next){
        if (isLogin) {
            ScreenLoader(isLoading = isLoading) {
            }
            LaunchedEffect(Unit) {
                delay(1500)
                appViewModel.setIsLoading(false)
            }
            if (!isLoading){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){

                    Button(onClick = {
                        appViewModel.setAuthenticated(true)
                        appViewModel.setIsLogin(true)
                    }) {
                        Text(text = "Login")
                    }
                    TextButton(onClick = {
                        appViewModel.setIsLogin(false)
                        appViewModel.setIsLoading(true)
                    }) {
                        Text(text = "Register")
                    }
                }
            }
        }
        else {
            ScreenLoader(isLoading = isLoading) {}
            LaunchedEffect(Unit) {
                delay(1500)
                appViewModel.setIsLoading(false)
            }
            if (!isLoading){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Register")
                    }
                    TextButton(onClick = {
                        appViewModel.setIsLogin(true)
                        appViewModel.setIsLoading(true)
                    }) {
                        Text(text = "Login")
                    }
                }
            }

        }
    }

    return authenticated
}

@Composable
fun WelcomeScreen(onGetStarted: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(
                color = MaterialTheme.colorScheme.background
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Welcome to StudyBuddy!",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(horizontal = 16.dp)
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
            text = "StudyBuddy helps you:",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .clip(RoundedCornerShape(size = 16.dp))
                .background(
                    color = MaterialTheme.colorScheme.inverseSurface,
                ),
        ) {
            Column (
                modifier = Modifier.padding(5.dp)
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
            onClick = onGetStarted,
            modifier = Modifier
                .width(250.dp)
        ) {
            Text("Get Started")
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 1.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "•",
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}


