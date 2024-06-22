package com.example.xstudy.dashbord

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashbordScreen() {
    Scaffold (
        topBar = { DashbordScreenTopBar() }
    ){pandingValue ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(pandingValue)
        ){
            items(10) {
                Text(text = "Item $it", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private  fun DashbordScreenTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Study Smart",
                style = MaterialTheme.typography.titleMedium
            )
        }
    )
}