package com.example.xstudy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.xstudy.dashbord.DashboardScreen
import com.example.xstudy.domain.model.Session
import com.example.xstudy.domain.model.Subject
import com.example.xstudy.domain.model.Task
import com.example.xstudy.module.SubjectScreen
import com.example.xstudy.task.TaskScreen
import com.example.xstudy.ui.theme.XstudyTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XstudyTheme {
               // DashboardScreen()
                //SubjectScreen()
                TaskScreen()
            }
        }
    }
}

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
