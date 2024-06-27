package com.example.xstudy.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun ForgotPasswordDialog(
    isOpen: Boolean,
    title: String ="Password Reset?",
    registerPassword: String,
    registerConfirmPassword: String,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
){
    var registerPasswordError by rememberSaveable { mutableStateOf<String?>(null) }
    var registerConfirmPasswordError by rememberSaveable { mutableStateOf<String?>(null) }
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

    registerOverAllError = when{
        registerPasswordError != null -> registerPasswordError.orEmpty()
        registerConfirmPasswordError != null -> registerConfirmPasswordError.orEmpty()
        else -> null
    }

    if (isOpen){
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.tertiary
            )},
            text = {
                Column {
                    Text(
                        text = "Are yore sure you want to reset your password? This action cannot be undone.",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    if (registerOverAllError == null){
                        Text(
                            text = "This will be your new login password after resetting.",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    else{
                        Text(
                            text = registerOverAllError.orEmpty(),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Red
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row (
                        modifier = Modifier
                            .width(300.dp)
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
                                    text = "Confirm Pas...",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )},
                                singleLine = true,
                                isError = registerConfirmPasswordError != null && registerConfirmPassword.isNotBlank(),
                            )
                        }
                    }
                }
            },
            dismissButton = {
                ElevatedButton(
                    modifier = Modifier
                        .width(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    onClick = onDismissRequest

                ) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                ElevatedButton(
                    modifier = Modifier
                        .width(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    enabled = registerPasswordError == null && registerConfirmPasswordError == null ,
                    onClick = onConfirmButtonClick
                ) {
                    Text(text = "Reset")
                }
            }
        )
    }

}