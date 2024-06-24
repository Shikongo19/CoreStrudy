package com.example.xstudy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.xstudy.domain.model.Subject

@Composable
fun AddScreenDialog(
    isOpen: Boolean,
    title: String ="ADD / UPDATE Subject",
    subjectName: String,
    goalHours: String,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    onSubjectNameChange: (String) -> Unit,
    onGoalHoursChange: (String) -> Unit,
    selectedColors: Color ,
    onColorChange: (Color) -> Unit
){
    var subjectNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var subjectGoalHoursError by rememberSaveable { mutableStateOf<String?>(null) }

    subjectNameError = when{
        subjectName.isBlank() -> "Please enter subject name !!!"
        subjectName.length < 4 -> "Subject name is too short!!!"
        subjectName.length > 20 -> "Subject name is too long !!!"
        else -> null
    }

    subjectGoalHoursError = when{
        goalHours.isBlank() -> "Please enter goal study hour(s) !!!"
        goalHours.toFloatOrNull() == null -> "Invalid number. Please provide digit from 0 to 9 !!!"
        goalHours.toFloat() < 1f -> "Please set at least 1 hour(s) !!!"
        goalHours.toFloat() > 1000f -> "Please set a maximum of 1000 hour(s) of studying !!!"
        else -> null
    }

    if (isOpen){
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title)},
            text = {
                Column {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Subject.subjectCardColors.forEach { color ->
                            Box(modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 2.dp,
                                    color = if (color == selectedColors) Color.Black
                                    else Color.Transparent,
                                    shape = CircleShape
                                )
                                .background(color = color)
                                .clickable { onColorChange(color) }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = subjectName,
                        onValueChange = onSubjectNameChange,
                        label = {
                            Text(text = "Enter Subject Name")},
                        singleLine = true,
                        isError = subjectNameError != null && subjectName.isNotBlank(),
                        supportingText = { Text(text = subjectNameError.orEmpty())}
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = goalHours,
                        onValueChange = onGoalHoursChange,
                        label = {
                            Text(text = "Enter Study Hours")},
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = subjectGoalHoursError != null && goalHours.isNotBlank(),
                        supportingText = { Text(text = subjectGoalHoursError.orEmpty())}
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmButtonClick,
                    enabled = subjectNameError == null && subjectGoalHoursError == null
                ) {
                    Text(text = "Save")
                }
            }
        )
    }

}