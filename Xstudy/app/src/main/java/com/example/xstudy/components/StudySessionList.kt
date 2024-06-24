package com.example.xstudy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xstudy.R
import com.example.xstudy.domain.model.Session

fun LazyListScope.studySessionsList(
    sectionTitle: String,
    sessions : List<Session>,
    emptyListText: String,
    onDeleteClick: (Session) -> Unit
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
    if (sessions.isEmpty()) {
        item {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier
                        .size(150.dp),
                    painter = painterResource(R.drawable.studying_rafiki),
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
    items(sessions){ session ->
        SessionCard(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            session = session,
            onDeleteClick = {onDeleteClick(session)}
        )
    }
}

@Composable
private fun SessionCard(
    modifier: Modifier = Modifier,
    session: Session,
    onDeleteClick: () -> Unit
){
    Card (modifier = modifier
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
            ){
                Text(
                    text = session.relatedToSubject,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.W700,
                    fontSize = 15.sp,
                    modifier = Modifier.width(230.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${session.date}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W700
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${session.duration} hr",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W700
            )
            IconButton(onClick = {onDeleteClick()}) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Session")
            }
        }
    }
}