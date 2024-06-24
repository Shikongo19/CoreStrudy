package com.example.xstudy.domain.model

import androidx.compose.ui.graphics.Color
import com.example.xstudy.ui.theme.onErrorContainerDark
import com.example.xstudy.ui.theme.onSecondaryLight
import com.example.xstudy.ui.theme.onTertiaryDark
import com.example.xstudy.ui.theme.outlineDark
import com.example.xstudy.ui.theme.secondaryLight
import com.example.xstudy.ui.theme.surfaceDark
import com.example.xstudy.ui.theme.tertiaryDark

data class Subject(
    val name: String,
    val goalHours: Float,
    val colors: Color,
    val subjectID: Int
){
    companion object{
        val subjectCardColors = listOf(onTertiaryDark, onErrorContainerDark, tertiaryDark, outlineDark)
    }
}
