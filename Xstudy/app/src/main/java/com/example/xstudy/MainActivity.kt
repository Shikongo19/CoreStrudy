package com.example.xstudy

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xstudy.authentication.Authentication
import com.example.xstudy.authentication.Login
import com.example.xstudy.authentication.Register
import com.example.xstudy.dashbord.DashBoardScreenRoute
import com.example.xstudy.domain.model.MotivationQuote
import com.example.xstudy.domain.model.Session
import com.example.xstudy.domain.model.Subject
import com.example.xstudy.domain.model.Task
import com.example.xstudy.home.HomeDisplay
import com.example.xstudy.navigation.Routes
import com.example.xstudy.repositories.AppViewModel
import com.example.xstudy.ui.theme.XstudyTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XstudyTheme {
                AppControl()
            }
        }
    }
}


val subjects = listOf(
    Subject(name = "English", goalHours = 12f, colors = Subject.subjectCardColors[0], subjectID = 0),
    Subject(name = "Math", goalHours = 12f, colors = Subject.subjectCardColors[1], subjectID = 1),
    Subject(name = "Physic", goalHours = 12f, colors = Subject.subjectCardColors[2], subjectID = 2),
    Subject(name = "Programming", goalHours = 12f, colors = Subject.subjectCardColors[0], subjectID = 3),
    Subject(name = "Biology", goalHours = 12f, colors = Subject.subjectCardColors[1], subjectID = 4),
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

val motivationalQuotes = listOf(
    MotivationQuote(1, "Perseverance", "The only way to do great work is to love what you do. - Steve Jobs"),
    MotivationQuote(2, "Belief", "Believe you can and you're halfway there. - Theodore Roosevelt"),
    MotivationQuote(3, "Success", "Success is not final, failure is not fatal: it is the courage to continue that counts. - Winston Churchill"),
    MotivationQuote(4, "Dreams", "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt"),
    MotivationQuote(5, "Imagination", "Logic will get you from A to B. Imagination will take you everywhere. - Albert Einstein"),
    MotivationQuote(6, "Courage", "It takes courage to grow up and become who you really are. - E.E. Cummings"),
    MotivationQuote(7, "Attitude", "Your attitude, not your aptitude, will determine your altitude. - Zig Ziglar"),
    MotivationQuote(8, "Determination", "The difference between the impossible and the possible lies in a person's determination. - Tommy Lasorda"),
    MotivationQuote(9, "Purpose", "The two most important days in your life are the day you are born and the day you find out why. - Mark Twain"),
    MotivationQuote(10, "Passion", "If you can't figure out your purpose, figure out your passion. For your passion will lead you right into your purpose. - Bishop T.D. Jakes"),
    MotivationQuote(11, "Change", "Be the change that you wish to see in the world. - Mahatma Gandhi"),
    MotivationQuote(12, "Effort", "The difference between ordinary and extraordinary is that little extra. - Jimmy Johnson"),
    MotivationQuote(13, "Persistence", "Fall seven times, stand up eight. - Japanese Proverb"),
    MotivationQuote(14, "Learning", "Live as if you were to die tomorrow. Learn as if you were to live forever. - Mahatma Gandhi"),
    MotivationQuote(15, "Ambition", "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt"),
    MotivationQuote(16, "Growth", "The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela"),
    MotivationQuote(17, "Wisdom", "The only true wisdom is in knowing you know nothing. - Socrates"),
    MotivationQuote(18, "Innovation", "Innovation distinguishes between a leader and a follower. - Steve Jobs"),
    MotivationQuote(19, "Vision", "The only thing worse than being blind is having sight but no vision. - Helen Keller"),
    MotivationQuote(20, "Opportunity", "Opportunities don't happen. You create them. - Chris Grosser"),
    MotivationQuote(21, "Action", "The way to get started is to quit talking and begin doing. - Walt Disney"),
    MotivationQuote(22, "Mindset", "Whether you think you can or you think you can't, you're right. - Henry Ford"),
    MotivationQuote(23, "Adversity", "Hardships often prepare ordinary people for an extraordinary destiny. - C.S. Lewis"),
    MotivationQuote(24, "Focus", "Concentrate all your thoughts upon the work at hand. The sun's rays do not burn until brought to a focus. - Alexander Graham Bell"),
    MotivationQuote(25, "Inspiration", "I have not failed. I've just found 10,000 ways that won't work. - Thomas A. Edison"),
    MotivationQuote(26, "Determination", "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time. - Thomas A. Edison"),
    MotivationQuote(27, "Self-belief", "Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle. - Christian D. Larson"),
    MotivationQuote(28, "Potential", "The potential of the average person is like a huge ocean unsailed, a new continent unexplored, a world of possibilities waiting to be released and channeled toward some great good. - Brian Tracy"),
    MotivationQuote(29, "Resilience", "The oak fought the wind and was broken, the willow bent when it must and survived. - Robert Jordan"),
    MotivationQuote(30, "Courage", "Courage doesn't always roar. Sometimes courage is the little voice at the end of the day that says I'll try again tomorrow. - Mary Anne Radmacher"),
    MotivationQuote(31, "Positivity", "Keep your face always toward the sunshine - and shadows will fall behind you. - Walt Whitman"),
    MotivationQuote(32, "Empowerment", "The most common way people give up their power is by thinking they don't have any. - Alice Walker"),
    MotivationQuote(33, "Creativity", "Creativity is intelligence having fun. - Albert Einstein"),
    MotivationQuote(34, "Dream Big", "All our dreams can come true, if we have the courage to pursue them. - Walt Disney"),
    MotivationQuote(35, "Persistence", "It's not whether you get knocked down, it's whether you get up. - Vince Lombardi"),
    MotivationQuote(36, "Self-Improvement", "There is only one corner of the universe you can be certain of improving, and that's your own self. - Aldous Huxley"),
    MotivationQuote(37, "Passion", "If you don't love what you do, you won't do it with much conviction or passion. - Mia Hamm"),
    MotivationQuote(38, "Perspective", "Two roads diverged in a wood, and I—I took the one less traveled by, And that has made all the difference. - Robert Frost"),
    MotivationQuote(39, "Empathy", "No one cares how much you know, until they know how much you care. - Theodore Roosevelt"),
    MotivationQuote(40, "Authenticity", "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment. - Ralph Waldo Emerson"),
    MotivationQuote(41, "Progress", "Even if you fall on your face, you're still moving forward. - Victor Kiam"),
    MotivationQuote(42, "Self-Confidence", "If you hear a voice within you say 'you cannot paint,' then by all means paint, and that voice will be silenced. - Vincent Van Gogh"),
    MotivationQuote(43, "Determination", "It does not matter how slowly you go as long as you do not stop. - Confucius"),
    MotivationQuote(44, "Innovation", "The best way to predict the future is to create it. - Peter Drucker"),
    MotivationQuote(45, "Leadership", "If your actions inspire others to dream more, learn more, do more and become more, you are a leader. - John Quincy Adams"),
    MotivationQuote(46, "Kindness", "No act of kindness, no matter how small, is ever wasted. - Aesop"),
    MotivationQuote(47, "Self-Discovery", "The only journey is the one within. - Rainer Maria Rilke"),
    MotivationQuote(48, "Growth Mindset", "The mind is everything. What you think you become. - Buddha"),
    MotivationQuote(49, "Resilience", "You may encounter many defeats, but you must not be defeated. - Maya Angelou"),
    MotivationQuote(50, "Purpose", "The purpose of life is not to be happy. It is to be useful, to be honorable, to be compassionate, to have it make some difference that you have lived and lived well. - Ralph Waldo Emerson")
)

 fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun AppControl() {
    val systemUiController = rememberSystemUiController()
    val isNavBarVisible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(Color.Unspecified)
        systemUiController.isNavigationBarVisible = false
    }

    LaunchedEffect(isNavBarVisible.value) {
        if (isNavBarVisible.value) {
            systemUiController.isNavigationBarVisible = true
            delay(1000)
            isNavBarVisible.value = false
            systemUiController.isNavigationBarVisible = false
        }
    }

    SwipeDetector(
        onSwipeUp = { isNavBarVisible.value = true }
    ) {
        // Your app content goes here
        AppContent()
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.App.routes) {

        composable(Routes.App.routes){
            App(navController)
        }

        composable(Routes.HomeDisplay.routes){
            HomeDisplay(navController)
        }

        composable(Routes.DashBoardScreenRoute.routes){
            DashBoardScreenRoute(navController)
        }

        composable(Routes.Login.routes){
            Login(navController = navController, appViewModel = appViewModel)
        }

        composable(Routes.Register.routes){
            Register(appViewModel = appViewModel, navController = navController)
        }

        composable(Routes.Authentication.routes){
            Authentication(navController = navController)
        }
    }
}

@Composable
fun SwipeDetector(
    onSwipeUp: () -> Unit,
    content: @Composable () -> Unit
) {
    val offsetY = remember { mutableStateOf(0f) }
    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        if (offsetY.value < -50) {
                            onSwipeUp()
                        }
                        offsetY.value = 0f
                    }
                ) { change, dragAmount ->
                    change.consume()
                    offsetY.value += dragAmount.y
                }
            }
    ) {
        content()
    }
}
