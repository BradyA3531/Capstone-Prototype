package com.example.learnkotlin.frontend.ui_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnkotlin.backend.database.audit_site.AuditSite
import com.example.learnkotlin.backend.database.blocked_site.BlockedSite

@Composable
fun AuditLogItem(item1: AuditSite, item2: BlockedSite){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = item2.siteName,
            fontSize = 20.sp,
            color = Color.Black
        )

        Text(
            text = item2.url,
            fontSize = 20.sp,
            color = Color.Black
        )

        Text(
            text = item1.timestamp.toString(),
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}