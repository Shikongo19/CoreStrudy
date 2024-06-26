package com.example.xstudy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xstudy.R
import com.example.xstudy.domain.model.Task
import com.example.xstudy.util.Priority

fun LazyListScope.taskList(
    sectionTitle: String,
    tasks : List<Task>,
    emptyListText: String,
    onTaskCardClick: (Int?) -> Unit,
    onCheckBoxClick: (Task) -> Unit
){
    item {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier.padding(12.dp)
        )
    }
    if (tasks.isEmpty()) {
        item {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier
                        .size(150.dp),
                    painter = painterResource(R.drawable.note_book),
                    contentDescription = "")

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = emptyListText,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            }
        }
    }
    items(tasks){ task ->
        TaskCard(
            task = task,
            onCheckBoxClick = {onCheckBoxClick(task)},
            onClick = {onTaskCardClick(task.taskID)}
        )
    }
}

@Composable
private fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onCheckBoxClick: () -> Unit,
    onClick: () -> Unit
){
    ElevatedCard (modifier = Modifier
        .padding(start = 10.dp, end = 10.dp, bottom = 6.dp)
        .clickable { onClick() }
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            TaskCheckBox(
                isComplete = task.isComplete,
                borderColor = Priority.fromInt(task.priority).color,
                onCheckBox = {onCheckBoxClick()}
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column (
                modifier.padding(start = 8.dp)
            ){
                Text(
                    text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = if (task.isComplete){
                        TextDecoration.LineThrough
                    }else{
                        TextDecoration.None
                    },
                    fontWeight = FontWeight.W700,
                    fontSize = 13 .sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${task.dueDate}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W700
                )
            }
        }
    }
}