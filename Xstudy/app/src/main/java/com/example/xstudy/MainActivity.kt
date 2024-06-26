package com.example.xstudy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xstudy.authentication.authentication
import com.example.xstudy.domain.model.Session
import com.example.xstudy.domain.model.Subject
import com.example.xstudy.domain.model.Task
import com.example.xstudy.loader.ScreenLoader
import com.example.xstudy.repositories.AppViewModel
import com.example.xstudy.ui.theme.XstudyTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XstudyTheme {
                val appViewModel: AppViewModel = viewModel()
                var isToNextPage by rememberSaveable { mutableStateOf(false) }
                val isLoading by appViewModel.isLoading.collectAsState()

                val authenticated by appViewModel.authenticated.collectAsState()

                if (authenticated){
                    appViewModel.setIsLoading(true)
                    LaunchedEffect(Unit) {
                        delay(1000)
                        isToNextPage = true
                    }
                    if (isToNextPage){
                        DestinationsNavHost(navGraph = NavGraphs.root)
                        appViewModel.setIsLoading(false)
                    }
                    ScreenLoader(isLoading = isLoading) {
                    }
                }
                else{
                    authentication(appViewModel)
                }

            }
        }
    }
}


val subjects = listOf(
    Subject(name = "English", goalHours = 12f, colors = Subject.subjectCardColors[0], subjectID = 0),
    Subject(name = "Math", goalHours = 12f, colors = Subject.subjectCardColors[2], subjectID = 1),
    Subject(name = "Physic", goalHours = 12f, colors = Subject.subjectCardColors[3], subjectID = 2),
    Subject(name = "Programming", goalHours = 12f, colors = Subject.subjectCardColors[1], subjectID = 3),
    Subject(name = "Biology", goalHours = 12f, colors = Subject.subjectCardColors[0], subjectID = 4),
    Subject(name = "Data Networks", goalHours = 12f, colors = Subject.subjectCardColors[2], subjectID = 5)
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

