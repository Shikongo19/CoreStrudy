package com.example.xstudy.domain.model

data class Session(
    val sessionID: Int,
    val relatedToSubject: String,
    val date:Long,
    val duration: Long,
    val sessionSubjectID: Int
)
