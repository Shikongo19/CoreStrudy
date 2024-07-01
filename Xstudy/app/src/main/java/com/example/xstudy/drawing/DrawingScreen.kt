package com.example.xstudy.drawing

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.xstudy.domain.model.Line
import com.example.xstudy.repositories.AppViewModel

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawingScreen(
    navController: NavController,
    appViewModel: AppViewModel
){
    var lines by remember { mutableStateOf(mutableListOf<Line>()) }
    var currentColor by remember { mutableStateOf(Color.Black) }

    Column {
        TopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.background),
            title = {
                Text(
                    text = "Select Color",
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            actions = {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = { currentColor = Color.Black},
                    modifier = Modifier
                        .padding(3.dp)
                        .width(30.dp)
                        .height(30.dp)
                ) {}
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    onClick = { currentColor = Color.Red },
                    modifier = Modifier
                        .padding(3.dp)
                        .width(30.dp)
                        .height(30.dp)
                ) {}
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    onClick = { currentColor = Color.Green },
                    modifier = Modifier
                        .padding(3.dp)
                        .width(30.dp)
                        .height(30.dp)
                ) {}
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    onClick = { currentColor = Color.Blue },
                    modifier = Modifier
                        .padding(3.dp)
                        .width(30.dp)
                        .height(30.dp)
                ) {}
            }
        )


        Divider()
        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.FormatPaint,
                contentDescription = "",
                tint = currentColor,
                modifier = Modifier.size(35.dp)
            )
            Button(
                modifier = Modifier
                    .padding(3.dp),
                onClick = {
                    if (lines.isNotEmpty()) {
                        lines = lines.removeLast()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(250, 138, 32))
            ) {
                Text(text = "Dell")
            }
            Button(
                modifier = Modifier
                    .padding(3.dp),
                onClick = {
                    lines = mutableListOf()
                },
                colors = ButtonDefaults.buttonColors(Color(250, 32, 32))
            ) {
                Text(text = "Clear")
            }
            Button(
                modifier = Modifier
                    .padding(3.dp),
                onClick = {
                    if (lines.isNotEmpty()) {
                        lines = lines.toMutableList().also { it.removeAt(it.lastIndex) }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(18, 140, 67))
            ) {
                Text(text = "Undo")
            }
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val line = Line(
                            start = change.position - dragAmount,
                            end = change.position,
                            color = currentColor
                        )
                        lines = (lines + line).toMutableList()
                        println("Line added: $line") // Debugging
                    }
                }
        ) {
            lines.forEach { line ->
                drawLine(
                    color = line.color,
                    start = line.start,
                    end = line.end,
                    strokeWidth = line.strokeWidth,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}