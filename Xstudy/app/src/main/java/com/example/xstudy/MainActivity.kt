package com.example.xstudy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.xstudy.domain.model.Session
import com.example.xstudy.domain.model.Subject
import com.example.xstudy.domain.model.Task
import com.example.xstudy.ui.theme.XstudyTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XstudyTheme {
               DestinationsNavHost(navGraph = NavGraphs.root)
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
