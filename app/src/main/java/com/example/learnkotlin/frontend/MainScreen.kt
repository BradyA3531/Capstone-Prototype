package com.example.learnkotlin.frontend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnkotlin.backend.database.audit_site.AuditSiteViewModel
import com.example.learnkotlin.frontend.ui_elements.BlockedAppItem
import com.example.learnkotlin.frontend.ui_elements.Checkbox
import com.example.learnkotlin.frontend.ui_elements.InputField
import com.example.learnkotlin.backend.database.blocked_app.BlockedApp
import com.example.learnkotlin.backend.database.blocked_app.BlockedAppViewModel
import com.example.learnkotlin.backend.database.blocked_site.BlockedSiteViewModel

@Composable
fun MainScreen(
    blockedAppViewModel: BlockedAppViewModel,
    blockedSiteViewModel: BlockedSiteViewModel,
    auditSiteViewModel: AuditSiteViewModel
) {
    var tabItems = listOf("Apps", "Websites", "Audit Logs")

    var selectedTabIndex by remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()){
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Blue,
            contentColor = Color.White,
        ){
          tabItems.forEachIndexed { index, title ->
              Tab(
                  selected = selectedTabIndex == index,
                  onClick = {
                      selectedTabIndex = index
                  },
                  text = {
                      Text(
                          text = title,
                          style = TextStyle(fontSize = 18.sp)
                      )
                  }
              )
          }
        }
        when (selectedTabIndex){
            0 -> BlockedAppDisplay(blockedAppViewModel)
            1 -> BlockedSiteDisplay(blockedSiteViewModel)
            2 -> AuditLogDisplay(blockedSiteViewModel, auditSiteViewModel)
        }
    }

}