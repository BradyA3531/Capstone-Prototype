package com.example.learnkotlin.frontend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.learnkotlin.backend.database.blocked_app.BlockedApp
import com.example.learnkotlin.backend.database.blocked_site.BlockedSite
import com.example.learnkotlin.backend.database.blocked_site.BlockedSiteViewModel
import com.example.learnkotlin.frontend.ui_elements.BlockedAppItem
import com.example.learnkotlin.frontend.ui_elements.BlockedWebsiteItem
import com.example.learnkotlin.frontend.ui_elements.Checkbox
import com.example.learnkotlin.frontend.ui_elements.InputField

@Composable
fun BlockedSiteDisplay(viewModel: BlockedSiteViewModel) {

    var site by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    var isBlocked by remember { mutableStateOf(false) }
    var isAudited by remember { mutableStateOf(false) }

    val appList by viewModel.blockedSites.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between items
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(
            label = "Website Name",
            onValueChanged = { site = it },
            placeholder = "Enter name of site"
        )

        InputField(
            label = "Url",
            onValueChanged = { url = it },
            placeholder = "Enter url of site"
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly){

            Checkbox(onCheckedChange = { isBlocked = it }, "Block Site")
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(onCheckedChange = { isAudited = it }, "Audit Site")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.addBlockedSite(BlockedSite(siteName = site, url = url, isBlocked = isBlocked, isAudited = isAudited))
        }) {
            Text("Save")
        }

        appList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { index: Int, item: BlockedSite ->
                        BlockedWebsiteItem(
                            item = item,
                            onDelete = {
                                viewModel.deleteBlockedSite(item)
                            },
                            onUpdateBlocked = {
                                item.isBlocked = !item.isBlocked
                                viewModel.updateBlockedSite(item)
                            },
                            onUpdateAudited = {
                                item.isAudited = !item.isAudited
                                viewModel.updateBlockedSite(item)
                            }
                        )
                    }
                }
            )
        }
    }
}