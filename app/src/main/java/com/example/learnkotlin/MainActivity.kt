package com.example.learnkotlin


import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.*
import com.example.learnkotlin.backend.business_logic.BlockerAccessibilityService
import com.example.learnkotlin.backend.database.audit_site.AuditSiteViewModel
import com.example.learnkotlin.backend.database.blocked_app.BlockedAppViewModel
import com.example.learnkotlin.backend.database.blocked_site.BlockedSiteViewModel
import com.example.learnkotlin.frontend.MainScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val blockedAppViewModel = ViewModelProvider(this)[BlockedAppViewModel::class.java]
        val blockedSiteViewModel = ViewModelProvider(this)[BlockedSiteViewModel::class.java]
        val auditSiteViewModel = ViewModelProvider(this)[AuditSiteViewModel::class.java]
        enableEdgeToEdge()
        //startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        val wasRedirected = intent.getBooleanExtra("redirected_from_block", false)
        setContent {
            var showAccessibilityDialog by remember {
                mutableStateOf(!isAccessibilityServiceEnabled() && !wasRedirected)
            }
            var showBlockedAppDialog by remember {
                mutableStateOf(wasRedirected)
            }

            MainScreen(
                blockedAppViewModel,
                blockedSiteViewModel,
                auditSiteViewModel
            )

            if (showAccessibilityDialog) {
                EnableAccessibilityDialog(
                    onDismiss = { showAccessibilityDialog = false },
                    onConfirm = {
                        openAccessibilitySettings()
                        showAccessibilityDialog = false
                    }
                )
            }

            if (showBlockedAppDialog) {
                BlockedAppDetectedDialog(
                    onDismiss = { showBlockedAppDialog = false }
                )
            }
        }

        if (wasRedirected) {
            intent.removeExtra("redirected_from_block")
        }
    }

    @Composable
    fun EnableAccessibilityDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Enable Accessibility Service") },
            text = { Text("To block content, please enable the Accessibility Service for this app.") },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text("Enable")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }

    @Composable
    fun BlockedAppDetectedDialog(onDismiss: () -> Unit) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Blocked App Detected") },
            text = { Text("You tried opening a blocked app. Stay focused!") },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("OK")
                }
            }
        )
    }

    private fun openAccessibilitySettings() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val componentName = ComponentName(this, BlockerAccessibilityService::class.java)
        val enabledServices = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
        val colonSplitter = TextUtils.SimpleStringSplitter(':')
        colonSplitter.setString(enabledServices ?: "")
        while (colonSplitter.hasNext()) {
            val componentNameString = colonSplitter.next()
            if (componentNameString.equals(componentName.flattenToString(), ignoreCase = true)) {
                return true
            }
        }
        return false
    }
}


