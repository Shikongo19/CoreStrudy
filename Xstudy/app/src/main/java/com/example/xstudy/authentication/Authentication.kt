package com.example.xstudy.authentication

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.xstudy.R
import com.example.xstudy.loader.ScreenLoader
import com.example.xstudy.repositories.AppViewModel
import kotlinx.coroutines.delay

@Composable
fun authentication(appViewModel: AppViewModel): Boolean {
    val isLoading by appViewModel.isLoading.collectAsState()
    var next by rememberSaveable { mutableStateOf(false) }
    var isForgotPassword  by rememberSaveable {mutableStateOf(false)}

    var loginUsername by rememberSaveable { mutableStateOf("") }
    var loginPassword by rememberSaveable { mutableStateOf("") }

    var registerUsername by rememberSaveable { mutableStateOf("") }
    var registerPassword by rememberSaveable { mutableStateOf("") }
    var registerConfirmPassword by rememberSaveable { mutableStateOf("") }
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }

    val authenticated by appViewModel.authenticated.collectAsState()
    val isLogin by appViewModel.isLogin.collectAsState()

    val isLoggedOut by appViewModel.isLogout.collectAsState()
    val username by appViewModel.isUsername.collectAsState()

    var userNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var userPasswordError by rememberSaveable { mutableStateOf<String?>(null) }

    var registerPasswordError by rememberSaveable { mutableStateOf<String?>(null) }
    var registerConfirmPasswordError by rememberSaveable { mutableStateOf<String?>(null) }
    var registerUserNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var firstNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var lastNameError by rememberSaveable { mutableStateOf<String?>(null) }

    var overAllError by rememberSaveable { mutableStateOf<String?>(null) }
    var registerOverAllError by rememberSaveable { mutableStateOf<String?>(null) }

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

    if (!next){
        ScreenLoader(isLoading = isLoading) {}
        LaunchedEffect(Unit) {
            delay(1500)
            appViewModel.setIsLoading(false)
        }
        if (!isLoading){
            WelcomeScreen(
                onGetStarted = {
                    appViewModel.setIsLoading(true)
                    next = true
                }
            )
        }
    }
    else{
        if (!isLoggedOut){
            ScreenLoader(isLoading = isLoading) {}
            LaunchedEffect(Unit) {
                delay(1500)
                appViewModel.setIsLoading(false)
            }
            if (!isLoading){
                ReturningUserScreen(
                    username = username,
                    onContinue = {
                        appViewModel.setAuthenticated(true)
                        appViewModel.setIsLogin(true)
                    }
                )
            }

        }
        else{
            if (isLogin) {
                ScreenLoader(isLoading = isLoading) {
                }
                LaunchedEffect(Unit) {
                    delay(1500)
                    appViewModel.setIsLoading(false)
                }
                if (!isLoading){
                    Login(
                        loginUsername = loginUsername,
                        loginPassword = loginPassword,
                        onPasswordChange = { loginPassword = it },
                        onUsernameChange = { loginUsername = it },
                        userPasswordError = userPasswordError ,
                        userNameError = userNameError,
                        overAllError = overAllError,
                        appViewModel = appViewModel,
                        onForgotPasswordClick = { isForgotPassword = true}
                    )
                }
            }
            else {
                ScreenLoader(isLoading = isLoading) {}
                LaunchedEffect(Unit) {
                    delay(1500)
                    appViewModel.setIsLoading(false)
                }
                if (!isLoading){
                    Register(
                        registerUsername = registerUsername,
                        registerPassword = registerPassword,
                        registerConfirmPassword = registerConfirmPassword,
                        firstName = firstName,
                        lastName = lastName,
                        appViewModel = appViewModel,
                        registerPasswordError = registerPasswordError,
                        registerConfirmPasswordError = registerConfirmPasswordError,
                        registerUserNameError = registerUserNameError,
                        firstNameError = firstNameError,
                        lastNameError = lastNameError,
                        registerOverAllError = registerOverAllError,
                        onPasswordChange = { registerPassword = it },
                        onUsernameChange = { registerUsername = it },
                        onConfirmPasswordChange = { registerConfirmPassword = it},
                        onFirstNameChange = { firstName = it},
                        onLastNameChange = { lastName = it},
                        onRegisterButtonClick = {appViewModel.setIsLogin(true)}
                    )
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

@Composable
private fun Login(
    loginUsername: String,
    loginPassword: String,
    onPasswordChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    userPasswordError: String?,
    userNameError: String?,
    overAllError: String?,
    appViewModel: AppViewModel,
    onForgotPasswordClick: () -> Unit
){
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
                        .fillMaxWidth()
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
                    onValueChange = onUsernameChange,
                    label = { Text(text = "Username",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                        )},
                    singleLine = true,
                    isError = userNameError != null && loginUsername.isNotBlank()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = loginPassword,
                    onValueChange = onPasswordChange,
                    label = { Text(text = "Password",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                        )},
                    singleLine = true,
                    isError = userPasswordError != null && loginPassword.isNotBlank(),
                )

                Spacer(modifier = Modifier.height(40.dp))
                ElevatedButton(
                    modifier = Modifier
                        .width(250.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    enabled = userNameError == null && userPasswordError == null,
                    onClick = { appViewModel.setAuthenticated(true) }
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
                            .clickable { onForgotPasswordClick() }
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
                                appViewModel.setIsLogin(false)
                                appViewModel.setIsLoading(true)
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

@Composable
private fun Register(
    registerUsername: String,
    registerPassword: String,
    registerConfirmPassword: String,
    firstName: String,
    lastName: String,
    appViewModel: AppViewModel,
    registerPasswordError: String?,
    registerConfirmPasswordError: String?,
    registerUserNameError: String?,
    firstNameError: String?,
    lastNameError: String?,
    registerOverAllError: String?,
    onPasswordChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onRegisterButtonClick: () -> Unit
){
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
                    .fillMaxWidth(),
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
                            onValueChange = onFirstNameChange,
                            label = { Text(text = "First Name",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                                )},
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
                            onValueChange = onLastNameChange,
                            label = { Text(text = "Last Name",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                                )},
                            singleLine = true,
                            isError = lastNameError != null && lastName.isNotBlank(),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = registerUsername,
                    onValueChange = onUsernameChange,
                    label = { Text(text = "Username",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                        )},
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
                            onValueChange = onPasswordChange,
                            label = { Text(text = "Password",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )},
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
                            onValueChange = onConfirmPasswordChange,
                            label = { Text(
                                text = "Confirm Password",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )},
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
                        onRegisterButtonClick()
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
                                appViewModel.setIsLogin(true)
                                appViewModel.setIsLoading(true)
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
