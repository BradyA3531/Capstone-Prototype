package com.example.learnkotlin.backend.business_logic

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import com.example.learnkotlin.MainActivity
import com.example.learnkotlin.MainApplication
import com.example.learnkotlin.backend.database.blocked_app.BlockedAppDAO
import kotlinx.coroutines.runBlocking

class BlockerAccessibilityService : AccessibilityService() {

    private lateinit var blockedAppDao: BlockedAppDAO
    private var lastCheckedPackage: String? = null

    override fun onServiceConnected() {
        super.onServiceConnected()
        blockedAppDao = MainApplication.appDatabase.blockedAppDao

        serviceInfo = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            notificationTimeout = 100
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString() ?: return

            if (packageName == lastCheckedPackage) return  // prevent repeat checks
            lastCheckedPackage = packageName

            val isBlocked = runBlocking {
                blockedAppDao.getAllNow().any { it.appName == packageName && it.isBlocked }
            }

            if (isBlocked) {
                showToast("Blocked app detected: $packageName")
                redirectToMainActivity()
            }
        }
    }

    override fun onInterrupt() {
        // No action required
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun redirectToMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("redirected_from_block", true)  // pass extra info
        }
        startActivity(intent)
    }

}