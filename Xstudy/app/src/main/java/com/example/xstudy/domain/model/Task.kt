package com.example.xstudy.domain.model

data class Task (
    val title: String,
    val description: String,
    val dueDate: Long,
    val priority: Int,
    val relatedSubject: String,
    val isComplete: Boolean,
    val taskID: Int,
    val taskSubjectID: Int
)
