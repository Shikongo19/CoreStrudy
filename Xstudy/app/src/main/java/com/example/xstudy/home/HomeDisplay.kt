package com.example.xstudy.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.xstudy.components.AddScreenDialog
import com.example.xstudy.components.BottomCardSection
import com.example.xstudy.components.DeleteDialog
import com.example.xstudy.components.QuizeCardSection
import com.example.xstudy.components.SubjectCardSection
import com.example.xstudy.domain.model.Subject
import com.example.xstudy.domain.model.Users
import com.example.xstudy.navigation.Routes
import com.example.xstudy.repositories.AppViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDisplay(navController: NavController) {
    val subjects = listOf("Math", "Science", "History", "English", "Art")
    val appViewModel = AppViewModel()
    val isLogin = appViewModel.isLogin.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()

    var isAddSubject  by rememberSaveable { mutableStateOf(false) }
    var isDeleteSession  by rememberSaveable { mutableStateOf(false) }

    var subjectName  by remember { mutableStateOf("") }
    var subjectGoalHours  by remember { mutableStateOf("") }

    var selectedColor  by remember { mutableStateOf(Subject.subjectCardColors.random()) }


    AddScreenDialog(
        isOpen = isAddSubject,
        subjectName = subjectName,
        goalHours = subjectGoalHours,
        onDismissRequest = { isAddSubject = false},
        onConfirmButtonClick = { isAddSubject = false},
        onSubjectNameChange = {subjectName = it},
        onGoalHoursChange = {subjectGoalHours = it},
        selectedColors = selectedColor,
        onColorChange = {selectedColor = it}
    )

    DeleteDialog(
        isOpen = isDeleteSession,
        title = "Delete Session?",
        body = "Are you sure you want to delete this session? Your studied hours will de reduced" +
                " by this session time. NB: This action can not be undone.",
        onDismissRequest = { isDeleteSession = false},
        onConfirmButtonClick = { isDeleteSession = false}
    )

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            homeScreenTopAppBar(
                userName = "Giideon V",
                onProfileClick = {  },
                onLogoutClick = {  },
                scrollBehavior = scrollBehavior
            )
        },
//        bottomBar = {
//            BottomCardSection(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(10.dp),
//                subjectCount = 5,
//                studiedHours = "9",
//                goalHours = "5"
//            )
//        },
    ){ paddingValues ->
        LazyColumn (
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            item {
                QuizeCardSection(
                    modifier = Modifier.fillMaxWidth(),
                    onAddIconClick = { /*TODO*/ },
                    quizeName = "math",
                    onSubjectCardClick = {}
                )
            }
            item { MotivationalQuote(appViewModel.didYouKnow.value) }
        }
    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun homeScreenTopAppBar(
    userName: String,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
){
    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = onProfileClick) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "profile")
            }
        },
        title = {
            Column {
                Text(
                    text = userName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.W700,
                    fontSize = 20.sp
                )
                Text(
                    text = "Welcome back!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

        },
        actions = {
            IconButton(onClick = onLogoutClick) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Delete Subject")
            }
        }
    )
}