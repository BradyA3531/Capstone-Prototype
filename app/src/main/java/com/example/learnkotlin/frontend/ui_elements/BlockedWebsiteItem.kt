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
import com.example.learnkotlin.backend.database.blocked_site.BlockedSite

@Composable
fun BlockedWebsiteItem(
    item: BlockedSite,
    onDelete : () -> Unit,
    onUpdateBlocked : () -> Unit,
    onUpdateAudited : () -> Unit,
) {
    var isBlocked by remember { mutableStateOf(false) }
    isBlocked = item.isBlocked

    var isAudited by remember {mutableStateOf(false)}
    isAudited = item.isAudited

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
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){
                Text(
                    text = item.siteName,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    text = item.url,
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }

            Text(
                text = "blocked: ${isBlocked}",
                fontSize = 20.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Audited: ${isAudited}",
                fontSize = 20.sp,
                color = Color.DarkGray
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Button(
                    onClick = onDelete,
                ){
                    Text("Delete")
                }

                Button(
                    onClick = {
                        onUpdateBlocked()
                        isBlocked = item.isBlocked
                    }
                ){
                    if(isBlocked)
                        Text("Unblock")
                    else
                        Text("Block")
                }

                Button(
                    onClick = {
                        onUpdateAudited()
                        isAudited = item.isAudited
                    }
                ){
                    if(isAudited)
                        Text("Stop Auditing")
                    else
                        Text("Audit")
                }
            }
        }
    }
}