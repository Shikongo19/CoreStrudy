package com.example.xstudy.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomCardSection(
    modifier: Modifier,
    subjectCount: Int,
    studiedHours: String,
    goalHours: String
){
    Row (modifier = modifier){
        BottomCountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject",
            count = "$subjectCount")

        Spacer(modifier = Modifier.width(10.dp))
        BottomCountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = studiedHours)
        Spacer(modifier = Modifier.width(10.dp))
        BottomCountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Hours",
            count = goalHours)
    }
}