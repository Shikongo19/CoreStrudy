package com.example.xstudy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.xstudy.R

@Composable
fun QuizeCard(
    modifier: Modifier = Modifier,
    subjectName: String,
    grdientColor: Color,
    onClick: () -> Unit,
    image: Int
){
    Box (
        modifier = modifier
            .width(100.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(size = 10.dp))
            .padding(start = 10.dp)
    ){
        Column {
            Box (
                modifier = modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(size = 10.dp))
                    .clickable { onClick() }
                    .background(
                        color = grdientColor
                    )
            ){
                Column (
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(image),
                        contentDescription ="",
                        modifier =  Modifier.fillMaxSize()
                    )

                }
            }
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = subjectName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            }

        }


    }
}