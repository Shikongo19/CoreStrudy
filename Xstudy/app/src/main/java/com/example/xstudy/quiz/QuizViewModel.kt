package com.example.xstudy.quiz

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xstudy.domain.model.Difficulty
import com.example.xstudy.domain.model.MathQuiz
import com.example.xstudy.quiz.math.QuizScorer
import kotlin.random.Random

// MathQuiz data class and QuizScorer class (as defined in the previous example)
// ...

class QuizViewModel : ViewModel() {
    private val quizzes = mutableStateListOf<MathQuiz>()
    private val scorer = QuizScorer()

    var currentQuestionIndex by mutableStateOf(0)
    var userAnswer by mutableStateOf("")
    var isQuizFinished by mutableStateOf(false)
    var feedbackMessage by mutableStateOf("")


    private val mathQuizzes = listOf(
        MathQuiz(1, "What is 5 + 7?", 12.0, Difficulty.EASY, marks = 2),
        MathQuiz(2, "Calculate 8 * 9", 72.0, Difficulty.EASY, marks = 1),
        MathQuiz(3, "What is 15 - 6?", 9.0, Difficulty.EASY, marks = 5),
        // Add more questions as needed
    )

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        quizzes.addAll(mathQuizzes.shuffled().take(5))
    }

    fun getCurrentQuestion(): MathQuiz? {
        return if (currentQuestionIndex < quizzes.size) quizzes[currentQuestionIndex] else null
    }

    fun getAllQuestions(): List<MathQuiz> {
        return mathQuizzes
    }
    // Function to get a random question
    fun getRandomQuestion(): MathQuiz {

        return mathQuizzes[Random.nextInt(mathQuizzes.size)]
    }

    // Function to get questions by difficulty
    fun getQuestionsByDifficulty(difficulty: Difficulty): List<MathQuiz> {
        return mathQuizzes.filter { it.difficulty == difficulty }
    }

    // Function to get a random question by difficulty
    fun getRandomQuestionByDifficulty(difficulty: Difficulty): MathQuiz {
        val filteredQuestions = getQuestionsByDifficulty(difficulty)
        return filteredQuestions[Random.nextInt(filteredQuestions.size)]
    }

    fun submitAnswer() {
        val currentQuestion = getCurrentQuestion() ?: return
        val parsedAnswer = userAnswer.toDoubleOrNull()

        if (parsedAnswer != null) {
            scorer.submitAnswer(currentQuestion, parsedAnswer)
            feedbackMessage = if (Math.abs(currentQuestion.answer - parsedAnswer) < 0.001) {
                "Correct!"
            } else {
                "Incorrect. The correct answer is ${currentQuestion.answer}"
            }
            currentQuestionIndex++
            userAnswer = ""

            if (currentQuestionIndex >= quizzes.size) {
                isQuizFinished = true
            }
        } else {
            feedbackMessage = "Please enter a valid number"
        }
    }

    fun getScore(): Int = scorer.score
    fun getTotalQuestions(): Int = scorer.totalQuestions
    fun getScorePercentage(): Double = scorer.getScorePercentage()

    fun restartQuiz() {
        currentQuestionIndex = 0
        userAnswer = ""
        isQuizFinished = false
        feedbackMessage = ""
        scorer.score = 0
        scorer.totalQuestions = 0
        quizzes.clear()
        loadQuestions()
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun QuizScreen(viewModel: QuizViewModel = viewModel()) {
    val currentQuestion = viewModel.getCurrentQuestion()
    val isQuizFinished = viewModel.isQuizFinished

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isQuizFinished) {
            currentQuestion?.let { question ->
                Text(
                    text = "Question ${viewModel.currentQuestionIndex + 1}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = viewModel.userAnswer,
                    onValueChange = { viewModel.userAnswer = it },
                    label = { Text("Your Answer") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.submitAnswer() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = viewModel.feedbackMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (viewModel.feedbackMessage.startsWith("Correct")) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
            }
        } else {
            Text(
                text = "Quiz Finished!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Your Score: ${viewModel.getScore()} out of ${viewModel.getTotalQuestions()}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Percentage: ${String.format("%.2f", viewModel.getScorePercentage())}%",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.restartQuiz() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Restart Quiz")
            }
        }
    }
}

@Composable
fun QuizApp() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            QuizScreen()
        }
    }
}