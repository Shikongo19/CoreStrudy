package com.example.xstudy.home

import androidx.compose.runtime.Composable
import com.example.xstudy.motivationalQuotes
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeDisplay() {
    val subjects = listOf("Math", "Science", "History", "English", "Art")

    HomeScreen(
        firstName = "John",
        motivationalQuotes = motivationalQuotes,
        lastName = "Doe",
        subjects = subjects,
        onLogout = { /* Handle logout */ },
        onProfileClick = { /* Navigate to profile */ },
        onCardClick = { action -> /* Handle action card click */ },
        onSubjectClick = { subject -> /* Handle subject click */ }
    )
}