package com.example.learnkotlin.frontend.ui_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnkotlin.backend.database.blocked_app.BlockedApp

@Composable
fun BlockedAppItem(
    item: BlockedApp,
    onDelete : () -> Unit,
    onUpdate : () -> Unit,
) {
    var isBlocked by remember { mutableStateOf(false) }
    isBlocked = item.isBlocked

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.appName,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = "blocked: ${isBlocked}",
                fontSize = 20.sp,
                color = Color.DarkGray
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(
                    onClick = onDelete,
                ){
                    Text("Delete")
                }

                Button(
                    onClick = {
                        onUpdate()
                        isBlocked = item.isBlocked
                    }
                ){
                    if(isBlocked)
                        Text("Unblock")
                    else
                        Text("Block")
                }
            }
        }
    }
}