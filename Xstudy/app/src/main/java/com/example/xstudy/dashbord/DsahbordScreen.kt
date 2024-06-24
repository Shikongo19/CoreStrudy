package com.example.xstudy.dashbord

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xstudy.R
import com.example.xstudy.components.AddScreenDialog
import com.example.xstudy.components.CountCard
import com.example.xstudy.components.DeleteDialog
import com.example.xstudy.components.SubjectCard
import com.example.xstudy.components.studySessionsList
import com.example.xstudy.components.taskList
import com.example.xstudy.domain.model.Session
import com.example.xstudy.domain.model.Subject
import com.example.xstudy.domain.model.Task

@Composable
fun DashboardScreen(){

    val subjects = listOf(
        Subject(name = "English", goalHours = 12f, colors = Subject.subjectCardColors[0], subjectID = 0),
        Subject(name = "English", goalHours = 12f, colors = Subject.subjectCardColors[2], subjectID = 0),
        Subject(name = "English", goalHours = 12f, colors = Subject.subjectCardColors[3], subjectID = 0),
        Subject(name = "English", goalHours = 12f, colors = Subject.subjectCardColors[1], subjectID = 0),
        Subject(name = "English", goalHours = 12f, colors = Subject.subjectCardColors[0], subjectID = 0),
        Subject(name = "English", goalHours = 12f, colors = Subject.subjectCardColors[2], subjectID = 0)
    )

    val tasks = listOf(
        Task(title = "Prepare notes", description = "", dueDate = 0L, priority = 0, relatedSubject = "", isComplete = false, taskID = 0, taskSubjectID = 0),
        Task(title = "Study", description = "", dueDate = 0L, priority = 1, relatedSubject = "", isComplete = true, taskID = 0, taskSubjectID = 0),
        Task(title = "Take Quize", description = "", dueDate = 0L, priority = 2, relatedSubject = "", isComplete = false, taskID = 0, taskSubjectID = 0),
        Task(title = "Read Story Book", description = "", dueDate = 0L, priority = 1, relatedSubject = "", isComplete = false, taskID = 0, taskSubjectID = 0),
        Task(title = "Exercise", description = "", dueDate = 0L, priority = 0, relatedSubject = "", isComplete = true, taskID = 0, taskSubjectID = 0),
        Task(title = "Write test", description = "", dueDate = 0L, priority = 2, relatedSubject = "", isComplete = true, taskID = 0, taskSubjectID = 0),

    )

    val sessions = listOf(
        Session(sessionID = 0, relatedToSubject = "Math", date = 0L, duration = 3, sessionSubjectID = 1),
        Session(sessionID = 0, relatedToSubject = "Programming", date = 0L, duration = 3, sessionSubjectID = 1),
        Session(sessionID = 0, relatedToSubject = "MIT", date = 0L, duration = 3, sessionSubjectID = 1),
        Session(sessionID = 0, relatedToSubject = "Software Verifications And Validations", date = 0L, duration = 3, sessionSubjectID = 1),
    )

    var isAddSubject  by rememberSaveable {mutableStateOf(false)}
    var isDeleteSession  by rememberSaveable {mutableStateOf(false)}

    var subjectName  by remember {mutableStateOf("")}
    var subjectGoalHours  by remember {mutableStateOf("")}

    var selectedColor  by remember {mutableStateOf(Subject.subjectCardColors.random())}

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
        topBar = { DashBoardScreenTopBar()},
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
    ){ paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){

            item {
                CountCardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    subjectCount = 5,
                    studiedHours = "3",
                    goalHours = "12")
            }
            
            item { 
                SubjectCardSection(
                    modifier = Modifier.fillMaxWidth(),
                    onAddIconClick = {isAddSubject = true},
                    subjectlist = subjects
                )
            }

            item {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text = "Start Study Session")
                }
            }

            taskList(
                sectionTitle = "UPCOMING TASKS",
                emptyListText = "You don't have any upcomming tasks.\n " +
                        "Click the + buton in subject screen to add new task.",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            studySessionsList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyListText = "You don't have any recent study session.\n" +
                        "Start a study session to begin recording your progress.",
                sessions = sessions,
                onDeleteClick = {isDeleteSession = true}
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashBoardScreenTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Xstudy",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.W700,
                fontSize = 25.sp
            )
        },
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
    )
}

@Composable
private fun CountCardSection(
    modifier: Modifier,
    subjectCount: Int,
    studiedHours: String,
    goalHours: String
){
    Row (modifier = modifier){
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject",
            count = "$subjectCount")

        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = studiedHours)
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Hours",
            count = goalHours)
    }
}

@Composable
private fun SubjectCardSection(
    modifier: Modifier,
    onAddIconClick: () -> Unit,
    subjectlist: List<Subject>,
    emptyListText: String = "You don't have any subject yet. \n click the + button to add new subject"
){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "MODULES",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W700, fontSize = 18.sp),
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = onAddIconClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Subject")
            }
        }
        if (subjectlist.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.glasses_book),
                contentDescription = "")

            Text(
                text = emptyListText,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ){
            items(subjectlist){ subject ->
                SubjectCard(
                    subjectName = subject.name,
                    grdientColor = subject.colors,
                    onClick = {}
                )
            }
        }
    }
}