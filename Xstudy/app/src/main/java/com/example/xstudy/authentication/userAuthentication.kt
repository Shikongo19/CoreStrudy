package com.example.xstudy.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.xstudy.R
import com.example.xstudy.domain.model.Users
import com.example.xstudy.loader.ScreenLoader
import com.example.xstudy.navigation.Routes
import com.example.xstudy.repositories.AppViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun Authentication(navController: NavController) {
    val appViewModel: AppViewModel = viewModel()
    val isLogin by appViewModel.isLogin.collectAsState()


    if (isLogin){
        ReturningUserScreen(
            username = "Giideon",
            onContinue = {
                navController.navigate(Routes.HomeDisplay.routes)
                appViewModel.setIsLogin(true)
            }
        )
    }else{
        navController.navigate(Routes.Login.routes)
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


fun RegistrationAuth(
    registerUsername: String,
    registerPassword: String,
    firstName: String,
    lastName: String,
    appViewModel: AppViewModel
): Boolean{
    val allUsers = appViewModel.users
    var isUser: Boolean = false
    var isRegistration: Boolean = false

    allUsers.value.forEach { users ->
        if (users.email == registerUsername){
            isUser = true
        }
    }

    if (!isUser) {

        appViewModel.setUsers(
            Users(
                id = Random(999999999).nextInt(),
                firstName = firstName,
                lastName = lastName,
                email = registerUsername,
                password = registerPassword
            )
        )

        isRegistration = true
    }
    return isRegistration
}


fun LoginAuth(
        loginUsername: String,
        loginPassword: String,
        viewModel: AppViewModel
    ): Boolean
{
    val allUsers = viewModel.users
    var isLogin: Boolean = false
    var isUser: Boolean = false

    allUsers.value.forEach { users ->
        if (
            users.email == loginUsername && users.password == loginPassword
        )
        {
            isUser = true
        }
    }

    if (isUser){
        isLogin = true
    }

    return isLogin
}