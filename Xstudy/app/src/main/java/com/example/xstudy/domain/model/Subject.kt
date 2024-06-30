package com.example.xstudy.domain.model

import androidx.compose.ui.graphics.Color
import com.example.xstudy.ui.theme.onErrorContainerDark
import com.example.xstudy.ui.theme.onSecondaryLight
import com.example.xstudy.ui.theme.onSurfaceDark
import com.example.xstudy.ui.theme.onSurfaceVariantDark
import com.example.xstudy.ui.theme.onTertiaryContainerDark
import com.example.xstudy.ui.theme.outlineDark
import com.example.xstudy.ui.theme.primaryDark
import com.example.xstudy.ui.theme.primaryLight
import com.example.xstudy.ui.theme.secondaryLight
import com.example.xstudy.ui.theme.surfaceDark
import com.example.xstudy.ui.theme.tertiaryDark
import com.example.xstudy.ui.theme.tertiaryLight

data class Subject(
    val name: String,
    val goalHours: Float,
    val colors: Color,
    val subjectID: Int
){
    companion object{
        val subjectCardColors = listOf(primaryDark, secondaryLight, tertiaryLight, onSurfaceDark)
        val QuizeCardColors = listOf(onSurfaceDark, secondaryLight, onSurfaceVariantDark, onErrorContainerDark)
        val OtherCardColors = listOf(primaryLight, tertiaryLight)
    }
}
