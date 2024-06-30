package com.example.xstudy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xstudy.R
import com.example.xstudy.home.getRandomColor
import com.example.xstudy.home.titleSmall


@Composable
fun ModuleScreen(modifier: Modifier){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            titleSmall(title = "Expand your knowledge")
        }

        ModuleCardRow(modifier = Modifier.fillMaxWidth(), nameOne = "Learn Java", nameTwo = "Learn Physics", onClickOne = {  }, imageOne = R.drawable.java, onClickTwo = { }, imageTwo = R.drawable.physics)
        ModuleCardRow(modifier = Modifier.fillMaxWidth(), nameOne = "Learn Math", nameTwo = "Learn AI", onClickOne = {  }, imageOne = R.drawable.math_logo, onClickTwo = { }, imageTwo = R.drawable.ai)
        ModuleCardRow(modifier = Modifier.fillMaxWidth(), nameOne = "Learn DSA 2", nameTwo = "Learn Software Processes", onClickOne = {  }, imageOne = R.drawable.data_algoritm, onClickTwo = { }, imageTwo = R.drawable.software_proceses)
        ModuleCardRow(modifier = Modifier.fillMaxWidth(), nameOne = "Learn HTML5", nameTwo = "Learn CSS3", onClickOne = {  }, imageOne = R.drawable.html, onClickTwo = { }, imageTwo = R.drawable.css3)
        ModuleCardRow(modifier = Modifier.fillMaxWidth(), nameOne = "learn JavaScript", nameTwo = "Learn Biology", onClickOne = {  }, imageOne = R.drawable.javascript, onClickTwo = { }, imageTwo = R.drawable.bilogy)
        ModuleCardRow(modifier = Modifier.fillMaxWidth(), nameOne = "Java", nameTwo = "Physics", onClickOne = {  }, imageOne = R.drawable.java, onClickTwo = { }, imageTwo = R.drawable.physics)
    }
}

@Composable
private fun ModuleCardRow(
    modifier: Modifier,
    nameOne: String,
    nameTwo: String,
    onClickOne: () -> Unit,
    imageOne: Int,
    onClickTwo: () -> Unit,
    imageTwo: Int
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ){
        ModuleCard(subjectName = nameOne, grdientColor = getRandomColor(), onClick = onClickOne, image = imageOne)
        ModuleCard(subjectName = nameTwo, grdientColor = getRandomColor(), onClick = onClickTwo, image = imageTwo)
    }
}
