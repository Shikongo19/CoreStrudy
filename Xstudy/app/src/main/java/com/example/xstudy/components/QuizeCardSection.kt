package com.example.xstudy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavController
import com.example.xstudy.R
import com.example.xstudy.domain.model.Subject
import com.example.xstudy.home.titleSmall
import com.example.xstudy.navigation.Routes

@Composable
fun QuizeCardSection(
    modifier: Modifier,
    navController: NavController
){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            titleSmall(title = "Kick Start")
        }

        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ){
            item{
                QuizeCard(
                    subjectName = "Start Study",
                    grdientColor = Subject.QuizeCardColors.random(),
                    onClick = {navController.navigate(Routes.DashBoardScreenRoute.routes)},
                    image = R.drawable.start
                )
                QuizeCard(
                    subjectName = "Take Quiz",
                    grdientColor = Subject.QuizeCardColors.random(),
                    onClick = {},
                    image = R.drawable.quize
                )
                QuizeCard(
                    subjectName = "Take Notes",
                    grdientColor = Subject.QuizeCardColors.random(),
                    onClick = {},
                    image = R.drawable.note_book
                )
                QuizeCard(
                    subjectName = "Make Drawings",
                    grdientColor = Subject.QuizeCardColors.random(),
                    onClick = {navController.navigate(Routes.DrawingScreen.routes)},
                    image = R.drawable.studying_rafiki
                )
            }
        }
    }
}