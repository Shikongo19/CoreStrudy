package com.example.xstudy.domain.model

// MathQuiz data class
data class MathQuiz(
    val id: Int,
    val question: String,
    val answer: Double,
    val difficulty: Difficulty,
    val marks: Int
)

// Enum for difficulty levels
enum class Difficulty {
    EASY, MEDIUM, HARD
}
