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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnkotlin.frontend.ui_elements.BlockedAppItem
import com.example.learnkotlin.frontend.ui_elements.Checkbox
import com.example.learnkotlin.frontend.ui_elements.InputField
import com.example.learnkotlin.backend.data.BlockedApp
import com.example.learnkotlin.backend.data.BlockedAppViewModel

@Composable
fun MainScreen(viewModel: BlockedAppViewModel) {

    var app by remember { mutableStateOf("") }
    var checkedState by remember { mutableStateOf(false) }

    val appList by viewModel.blockedApps.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between items
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(
            label = "App Name",
            onValueChanged = { app = it },
            placeholder = "Enter name of app"
        )

        Checkbox(onCheckedChange = { checkedState = it })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.addBlockedApp(BlockedApp(appName = app, isBlocked = checkedState))
        }) {
            Text("Save")
        }

        appList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { index: Int, item: BlockedApp ->
                        BlockedAppItem(
                            item = item,
                            onDelete = {
                                viewModel.deleteBlockedApp(item)
                            },
                            onUpdate = {
                                item.isBlocked = !item.isBlocked
                                viewModel.updateBlockedApp(item)
                            }
                        )
                    }
                }
            )
        }
    }
}