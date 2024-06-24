package com.example.xstudy.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color

enum class Priority(val title: String, val color: Color, val value: Int){
    LOW(title = "Low", color = Color(9, 92, 28), value = 0),
    MEDIUM(title = "Medium", color = Color(145, 90, 7), value = 1),
    HIGH(title = "High", color = Color(163, 3, 3), value = 2);

    companion object{
        fun fromInt(value: Int) = values().firstOrNull { it.value == value} ?: MEDIUM
    }
}