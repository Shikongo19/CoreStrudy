package com.example.xstudy.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.xstudy.navigation.Routes
import com.example.xstudy.repositories.AppViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Login(
    navController: NavController,
    appViewModel: AppViewModel,
){
    var isForgotPassword  by rememberSaveable { mutableStateOf(false) }

    var loginUsername by rememberSaveable { mutableStateOf("") }
    var loginPassword by rememberSaveable { mutableStateOf("") }

    var registerPassword by rememberSaveable { mutableStateOf("") }
    var registerConfirmPassword by rememberSaveable { mutableStateOf("") }


    var userNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var userPasswordError by rememberSaveable { mutableStateOf<String?>(null) }

    var overAllError by rememberSaveable { mutableStateOf<String?>(null) }


    userPasswordError = when{
        loginPassword.isBlank() -> "Please enter Password !!!"
        else -> null
    }

    userNameError = when{
        loginUsername.isBlank() -> "Please enter Username !!!"
        else -> null
    }
    overAllError = when{
        userNameError != null -> userNameError.toString()
        userPasswordError != null -> userPasswordError.toString()
        else -> null
    }

    ForgotPasswordDialog(
        isOpen = isForgotPassword,
        registerPassword = registerPassword,
        registerConfirmPassword = registerConfirmPassword,
        onDismissRequest = { isForgotPassword = false},
        onConfirmButtonClick = { appViewModel.setIsLogin(true)},
        onPasswordChange = {registerPassword = it},
        onConfirmPasswordChange = {registerConfirmPassword = it}
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(state = rememberScrollState())
            .horizontalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ){
            Spacer(modifier = Modifier.height(50.dp))
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Column (
                    modifier = Modifier
                        .width(320.dp)
                        .padding(horizontal = 45.dp)
                ){
                    Text(
                        text = "Login Now!",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    if (overAllError == null){
                        Text(
                            text = "Please enter your credentials to continue.",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                    else{
                        Text(
                            text = overAllError.orEmpty(),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Red
                        )
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = loginUsername,
                    onValueChange = {loginUsername = it},
                    label = { Text(text = "Username",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    },
                    singleLine = true,
                    isError = userNameError != null && loginUsername.isNotBlank()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = loginPassword,
                    onValueChange = { loginPassword = it },
                    label = { Text(text = "Password",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    },
                    singleLine = true,
                    isError = userPasswordError != null && loginPassword.isNotBlank(),
                )

                Spacer(modifier = Modifier.height(40.dp))
                ElevatedButton(
                    modifier = Modifier
                        .width(250.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    enabled = userNameError == null && userPasswordError == null,
                    onClick = { navController.navigate(Routes.HomeDisplay.routes) }
                ) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        text = "Forgot",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.inverseSurface,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = "password?",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .clickable {
                                isForgotPassword = true
                            }
                            .padding(end = 10.dp),
                        textDecoration = TextDecoration.Underline
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Row {
                    Text(
                        text = "Don't have an account?",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Routes.Register.routes)
                            }
                            .padding(start = 10.dp),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
