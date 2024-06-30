package com.example.xstudy.quiz.math

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xstudy.domain.model.Difficulty
import com.example.xstudy.domain.model.MathQuiz
import com.example.xstudy.quiz.QuizViewModel
import kotlin.random.Random




// Simple scoring system
class QuizScorer {
    var score = 0
    var totalQuestions = 0

    fun submitAnswer(question: MathQuiz, userAnswer: Double) {
        totalQuestions++
        if (isCorrect(question.answer, userAnswer)) {
            score++
        }
    }

    private fun isCorrect(correctAnswer: Double, userAnswer: Double): Boolean {
        // Using a small epsilon for floating-point comparison
        return Math.abs(correctAnswer - userAnswer) < 0.001
    }

    fun getScorePercentage(): Double {
        return if (totalQuestions > 0) (score.toDouble() / totalQuestions) * 100 else 0.0
    }
}

