package com.example.xstudy.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.xstudy.sessions
import com.example.xstudy.subjects
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


@Composable
fun SessionScreenRoute(
    navigator: DestinationsNavigator
){
    SessionScreen(
        onBackArrowClick = { navigator.popBackStack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SessionScreen(
    onBackArrowClick: () -> Unit
){

    var isDeleteTask  by rememberSaveable { mutableStateOf(false) }

    var isBottomSheetOpen  by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()

    SubjectListBottomSheet(
        sheetState = sheetState,
        isOpen = isBottomSheetOpen,
        subjects = subjects,
        onSubjectClick = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) isBottomSheetOpen = false
            }
        },
        onDismissRequest = {isBottomSheetOpen = false},
    )

    DeleteDialog(
        isOpen = isDeleteTask,
        title = "Delete Task?",
        body = "Are you sure you want to delete this Task? " +
                " NB: This action can not be undone.",
        onDismissRequest = { isDeleteTask = false},
        onConfirmButtonClick = { isDeleteTask = false}
    )

    Scaffold (
        topBar = {
            SessionScreenTopBar(
                onBackArrowClick = onBackArrowClick
            )
        }
    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            item {
                TimerSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }

            item {
                RelatedToSubjectSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    relatedToSubject = "English",
                    isBottomSheetOpen = {isBottomSheetOpen = true}
                )
            }

            item {
                ButtonSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    onStartButtonClick = {  },
                    onCancelButtonClick = {  },
                    onFinishButtonClick = {}
                )
            }

            studySessionsList(
                sectionTitle = "STUDY SESSIONS HISTORY",
                emptyListText = "You don't have any recent study session.\n" +
                        "Start a study session to begin recording your progress.",
                sessions = sessions,
                onDeleteClick = {isDeleteTask = true}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SessionScreenTopBar(
    onBackArrowClick: () -> Unit
){
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackArrowClick
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Navigation")
            }
        },
        title = {
            Text(
                text = "Study Sessions"
            )
        }
    )
}

@Composable
private fun TimerSection(
    modifier: Modifier
){
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Box (
            modifier = Modifier
                .size(250.dp)
                .border(5.dp, MaterialTheme.colorScheme.surfaceVariant, CircleShape)
        )
        Text(
            text = "00:00:00",
            style = MaterialTheme.typography.displayMedium,
        )
    }
}

@Composable
private fun RelatedToSubjectSection(
    modifier: Modifier,
    relatedToSubject: String,
    isBottomSheetOpen: () -> Unit
){
    Column (modifier = modifier){
        Text(
            text = "Select Subject",
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.W700
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = relatedToSubject)
            IconButton(onClick = isBottomSheetOpen) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select Subject")
            }
        }
    }
}

@Composable
private fun ButtonSection(
    modifier: Modifier,
    onStartButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
    onFinishButtonClick: () -> Unit
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Button(onClick = onCancelButtonClick) {
            Text(
                text = "Cancel",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }
        Button(onClick = onStartButtonClick) {
            Text(
                text = "Start",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }
        Button(onClick = onFinishButtonClick) {
            Text(
                text = "Finish",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            )
        }
    }

}