package com.example.learnkotlin.frontend

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.learnkotlin.backend.database.audit_site.AuditSite
import com.example.learnkotlin.backend.database.audit_site.AuditSiteViewModel
import com.example.learnkotlin.backend.database.blocked_app.BlockedApp
import com.example.learnkotlin.backend.database.blocked_site.BlockedSite
import com.example.learnkotlin.backend.database.blocked_site.BlockedSiteViewModel
import com.example.learnkotlin.frontend.ui_elements.AuditLogItem
import com.example.learnkotlin.frontend.ui_elements.BlockedAppItem
import com.example.learnkotlin.frontend.ui_elements.Checkbox
import com.example.learnkotlin.frontend.ui_elements.InputField

@Composable
fun AuditLogDisplay(
    blockedSiteViewModel: BlockedSiteViewModel,
    auditSiteViewModel: AuditSiteViewModel
){
    val appList by auditSiteViewModel.audits.observeAsState()
    val blockedSites by blockedSiteViewModel.blockedSites.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between items
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        appList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { index: Int, item: AuditSite ->
                        val blockedSite = blockedSites?.find { it.id == item.siteId }

                        AuditLogItem(
                            item1 = item,
                            item2 = blockedSite?: throw Resources.NotFoundException("Site not found: ${item.siteId}")
                        )
                    }
                }
            )
        }
    }
}
