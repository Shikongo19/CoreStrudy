package com.example.xstudy.home

import androidx.compose.runtime.Composable
import com.example.xstudy.domain.model.Users
import com.example.xstudy.motivationalQuotes
import com.example.xstudy.repositories.AppViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeDisplay() {
    val subjects = listOf("Math", "Science", "History", "English", "Art")
    val appViewModel = AppViewModel()

    HomeScreen(
        firstName = getUser(appViewModel).firstName,
        motivationalQuotes = motivationalQuotes,
        lastName = getUser(appViewModel).lastName,
        subjects = subjects,
        onLogout = { /* Handle logout */ },
        onProfileClick = { /* Navigate to profile */ },
        onCardClick = { action -> /* Handle action card click */ },
        onSubjectClick = { subject -> /* Handle subject click */ }
    )
}

fun getUser(
    appViewModel: AppViewModel
): Users {
    val users = appViewModel.users
    var currentUser: Users = Users(0, "", "", "", "")
    users.value.forEach { user ->
        if (user.email == appViewModel.isUsername.toString()) {
            currentUser = Users(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                password = user.password
            )
        }
    }
    return  currentUser
}