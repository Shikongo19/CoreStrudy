package com.example.xstudy.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.xstudy.components.TaskCheckBox
import com.example.xstudy.util.Priority

@Composable
fun TaskScreen(){

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var subjectNameError by rememberSaveable { mutableStateOf<String?>(null) }

    subjectNameError = when{
        title.isBlank() -> "Please enter task title !!!"
        title.length < 10 -> "task title is too short!!!"
        title.length > 30 -> "task title is too long !!!"
        else -> null
    }

    Scaffold (
        topBar = {
            SubjectScreenTopBar(
                isTaskExist = true,
                onBackArrowClick = {},
                onDeleteButtonClick = {},
                isCompleted = false,
                checkBoxBorderColor = Color.Red,
                onCheckBoxClick = {}
            )
        }
    ){ paddingValues ->
        LazyColumn (
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ){
            item {
                Column (
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    OutlinedTextField(
                        value = title,
                        onValueChange = {title = it},
                        label = {
                            Text(text = "Enter Task title")},
                        singleLine = true,
                        isError = subjectNameError != null && title.isNotBlank(),
                        supportingText = { Text(text = subjectNameError.orEmpty())}
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = {
                            Text(text = "Enter Task Description")},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Due Date",
                        modifier = Modifier.align(Alignment.Start),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W700
                    )
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = "25 June 2024")
                        IconButton(onClick = {  }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select Date")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Priority",
                        modifier = Modifier.align(Alignment.Start),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Priority.entries.forEach { priority ->
                            PriorityButton(
                                modifier = Modifier.weight(1f),
                                label = priority.title,
                                backgroundColor = priority.color,
                                borderColor = if (priority == Priority.MEDIUM) Color.White
                                else Color.Transparent,
                                labelColor = if (priority == Priority.MEDIUM) Color.White
                                else Color.White.copy(alpha = 0.7f),
                                onClick = {}
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Priority",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.W700
                        )
                        IconButton(onClick = {  }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Select Subject")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        enabled = subjectNameError == null,
                        onClick = {},
                        modifier = Modifier.align(Alignment.End)
                            .fillMaxWidth()
                            .padding(horizontal = 48.dp, vertical = 20.dp)
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreenTopBar(
    isTaskExist: Boolean,
    onBackArrowClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    isCompleted: Boolean,
    checkBoxBorderColor: Color,
    onCheckBoxClick: () -> Unit
){
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackArrowClick
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Navigation")
            }
        },
        title = { Text(text = "Task")},
        actions = {
            if (isTaskExist) {
                TaskCheckBox(
                    isComplete = isCompleted,
                    borderColor = checkBoxBorderColor,
                    onCheckBox = onCheckBoxClick
                )
                IconButton(onClick = onDeleteButtonClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Subject"
                    )
                }
            }
        }
    )
}

@Composable
private fun PriorityButton(
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color,
    borderColor: Color,
    labelColor: Color,
    onClick: () -> Unit

){
    Box (
        modifier = modifier
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(5.dp)
            .border(1.dp, borderColor, RoundedCornerShape(5.dp))
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = label, color = labelColor)
    }
}
