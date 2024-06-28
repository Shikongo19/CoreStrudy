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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.xstudy.repositories.AppViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun Register(
    appViewModel: AppViewModel,
    navController: NavController
){
    var isForgotPassword  by rememberSaveable { mutableStateOf(false) }

    var registerUsername by rememberSaveable { mutableStateOf("") }
    var registerPassword by rememberSaveable { mutableStateOf("") }
    var registerConfirmPassword by rememberSaveable { mutableStateOf("") }
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }


    var registerPasswordError by rememberSaveable { mutableStateOf<String?>(null) }
    var registerConfirmPasswordError by rememberSaveable { mutableStateOf<String?>(null) }
    var registerUserNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var firstNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var lastNameError by rememberSaveable { mutableStateOf<String?>(null) }

    var registerOverAllError by rememberSaveable { mutableStateOf<String?>(null) }


    registerPasswordError = when {
        registerPassword.isBlank() -> "Please enter Password !!!"
        registerPassword.length < 8 -> "Password is too short!!!"
        registerPassword.length > 20 -> "Password is too long !!!"
        registerPassword.count { it.isLetterOrDigit() } < 1 -> "Password must contain at least one letter and one number !!!"
        registerPassword.count { it.isUpperCase() } < 1 -> "Password must contain at least one capital letter !!!"
        registerPassword.count { !it.isLetterOrDigit() } < 1 -> "Password must contain at least one special character !!!"
        else -> null
    }

    registerConfirmPasswordError = when{
        registerConfirmPassword.isBlank() -> "Please enter Confirm Password !!!"
        registerConfirmPassword != registerPassword -> "Password does not match !!!"
        else -> null
    }

    registerUserNameError = when {
        registerUsername.isBlank() -> "Please enter Username !!!"
        !registerUsername[0].isUpperCase() -> "Username must start with a capital letter!!!"
        registerUsername.length < 6 -> "Username is too short!!!"
        registerUsername.length > 20 -> "Username is too long !!!"
        registerUsername.count { it.isLetterOrDigit() } < 2 -> "Username must contain at least two numbers!!!"
        else -> null
    }

    firstNameError = when {
        firstName.isBlank() -> "Please enter First Name !!!"
        firstName.length < 4 -> "First Name is too short!!!"
        firstName.length > 50 -> "First Name is too long !!!"
        firstName.any { !it.isLetter() &&it != ' ' && it != '-' } -> "First Name should only contain letters, spaces, or hyphens!!!"
        else -> null
    }

    lastNameError = when {
        lastName.isBlank() -> "Please enter last Name !!!"
        lastName.length < 4 -> "Last Name is too short!!!"
        lastName.length > 50 -> "Last Name is too long !!!"
        lastName.any { !it.isLetter() && it != ' ' && it != '-' } -> "Last Name should only contain letters, spaces, or hyphens!!!"
        else -> null
    }

    registerOverAllError = when{
        firstNameError != null -> firstNameError.orEmpty()
        lastNameError != null -> lastNameError.orEmpty()
        registerUserNameError != null -> registerUserNameError.orEmpty()
        registerPasswordError != null -> registerPasswordError.orEmpty()
        registerConfirmPasswordError != null -> registerConfirmPasswordError.orEmpty()

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
        verticalArrangement = Arrangement.Center,

        ){
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                modifier = Modifier
                    .width(320.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp)
                ){
                    Text(
                        text = "Sign Up Now!",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    if (registerOverAllError == null){
                        Text(
                            text = "Please enter your credentials to continue.",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                    else{
                        Text(
                            text = registerOverAllError.orEmpty(),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Red
                        )
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    modifier = Modifier
                        .width(290.dp)
                ){
                    Column (
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                    ){
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = { Text(text = "First Name",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            },
                            singleLine = true,
                            isError = firstNameError != null && firstName.isNotBlank(),
                        )
                    }
                    Column (
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                    ){
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text(text = "Last Name",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            },
                            singleLine = true,
                            isError = lastNameError != null && lastName.isNotBlank(),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = registerUsername,
                    onValueChange = { registerUsername = it },
                    label = { Text(text = "Username",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    },
                    singleLine = true,
                    isError = registerUserNameError != null && registerUsername.isNotBlank()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row (
                    modifier = Modifier
                        .width(290.dp)
                ){
                    Column (
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                    ){
                        OutlinedTextField(
                            value = registerPassword,
                            onValueChange = { registerPassword = it },
                            label = { Text(text = "Password",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            },
                            singleLine = true,
                            isError = registerPasswordError != null && registerPassword.isNotBlank(),
                            visualTransformation = PasswordVisualTransformation()
                        )
                    }
                    Column (
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                    ){
                        OutlinedTextField(
                            value = registerConfirmPassword,
                            onValueChange = { registerConfirmPassword = it },
                            label = { Text(
                                text = "Confirm Password",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            },
                            singleLine = true,
                            isError = registerConfirmPasswordError != null && registerConfirmPassword.isNotBlank(),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
                ElevatedButton(
                    modifier = Modifier
                        .width(250.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    enabled = registerUserNameError == null && registerPasswordError == null && registerConfirmPasswordError == null && firstNameError == null && lastNameError == null,
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text(text = "Register")
                }
                Spacer(modifier = Modifier.height(50.dp))
                Row {
                    Text(
                        text = "Already have an account?",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
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
