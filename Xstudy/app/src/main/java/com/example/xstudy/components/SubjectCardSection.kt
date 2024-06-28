package com.example.xstudy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xstudy.R
import com.example.xstudy.domain.model.Subject

@Composable
fun SubjectCardSection(
    modifier: Modifier,
    onAddIconClick: () -> Unit,
    subjectlist: List<Subject>,
    emptyListText: String = "You don't have any subject yet. \n click the + button to add new subject",
    onSubjectCardClick: (Int?) -> Unit
){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Your Subjects",
                style = MaterialTheme.typography.headlineSmall ,fontSize = 18.sp,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = onAddIconClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Subject")
            }
        }
        if (subjectlist.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.glasses_book),
                contentDescription = "")

            Text(
                text = emptyListText,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ){
            items(subjectlist){ subject ->
                SubjectCard(
                    subjectName = subject.name,
                    grdientColor = subject.colors,
                    onClick = {onSubjectCardClick(subject.subjectID)}
                )
            }
        }
    }
}