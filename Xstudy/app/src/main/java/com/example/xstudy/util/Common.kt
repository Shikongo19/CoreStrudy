package com.example.xstudy.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

enum class Priority(val title: String, val color: Color, val value: Int){
    LOW(title = "Low", color = Color(9, 92, 28), value = 0),
    MEDIUM(title = "Medium", color = Color(145, 90, 7), value = 1),
    HIGH(title = "High", color = Color(163, 3, 3), value = 2);

    companion object{
        fun fromInt(value: Int) = values().firstOrNull { it.value == value} ?: MEDIUM
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long?.changeMillisToDateString(): String{
    val date: LocalDate = this?.let {
        Instant
            .ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    } ?: LocalDate.now()
    return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
}