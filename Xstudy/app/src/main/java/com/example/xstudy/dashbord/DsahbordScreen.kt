package com.example.xstudy.dashbord

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.xstudy.components.AddScreenDialog
import com.example.xstudy.components.BottomCardSection
import com.example.xstudy.components.CountCard
import com.example.xstudy.components.DeleteDialog
import com.example.xstudy.components.SubjectCardSection
import com.example.xstudy.components.studySessionsList
import com.example.xstudy.components.taskList
import com.example.xstudy.domain.model.Subject
import com.example.xstudy.module.SubjectScreenNavArgs
import com.example.xstudy.sessions
import com.example.xstudy.subjects
import com.example.xstudy.task.TaskScreeNavArgn
import com.example.xstudy.tasks


@Composable
fun DashBoardScreenRoute(
    navController: NavController
){
    DashboardScreen(
        onSubjectCardClick = { subjectID ->
            subjectID?.let {
                val navArg = SubjectScreenNavArgs(subjectID = subjectID)
                //navigator.navigate(SubjectScreenRouteDestination(navArgs = navArg))
            }
        },
        onTaskCardClick = { taskID ->
            val navArg = TaskScreeNavArgn(subjectID = null, taskID = taskID)
            //navigator.navigate(TaskScreenRouteDestination(navArgs = navArg))
        },
        onStartSessionButtonClick = {
            //navigator.navigate(SessionScreenRouteDestination())
        }
    )
}

@Composable
private fun DashboardScreen(
    onSubjectCardClick: (Int?) -> Unit,
    onTaskCardClick: (Int?) -> Unit,
    onStartSessionButtonClick: () -> Unit
){
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
//        bottomBar = {
//            BottomCardSection(
//                modifier = Modifier.fillMaxWidth().padding(10.dp),
//                subjectCount = 5,
//                studiedHours = "29",
//                goalHours = "55"
//            )
//        },
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
                    subjectlist = subjects,
                    onSubjectCardClick = onSubjectCardClick
                )
            }

            item {
                Button(
                    onClick = onStartSessionButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text = "Start Study Session")
                }
            }

            taskList(
                sectionTitle = "UPCOMING TASKS",
                emptyListText = "You don't have any upcoming tasks.\n " +
                        "Click the + button in subject screen to add new task.",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = onTaskCardClick
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
