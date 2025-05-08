package com.example.learnkotlin.backend.business_logic

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.example.learnkotlin.MainApplication
import com.example.learnkotlin.backend.database.audit_site.AuditSite
import com.example.learnkotlin.backend.database.audit_site.AuditSiteDAO
import com.example.learnkotlin.backend.database.blocked_site.BlockedSiteDAO
import kotlinx.coroutines.runBlocking
import java.util.Date

class UrlLoggingService : AccessibilityService() {

    private lateinit var blockedSiteDAO: BlockedSiteDAO
    private lateinit var auditSiteDAO: AuditSiteDAO

    private val TAG = "UrlLoggingService"
    private var lastLoggedUrl: String? = null

    override fun onServiceConnected() {
        val info = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
            packageNames = arrayOf("com.android.chrome")
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            notificationTimeout = 100
        }
        serviceInfo = info
        blockedSiteDAO = MainApplication.appDatabase.blockedSiteDao
        auditSiteDAO = MainApplication.appDatabase.auditSiteDAO
        Log.d(TAG, "Accessibility Service connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event ?: return

        val rootNode = rootInActiveWindow ?: return

        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED && event.packageName != null) {
            val url = extractUrl(rootNode)
            url?.let { currentUrl ->
                if (currentUrl != lastLoggedUrl && currentUrl != "") {              // perform logging action here for database
                    Log.d(TAG, "Visited URL: $currentUrl")
                    lastLoggedUrl = currentUrl
                    val isMonitored = runBlocking {
                        blockedSiteDAO.getAllNow().any{it.url.contains(currentUrl) && it.isAudited == true}
                    }

                    if(isMonitored){
                        runBlocking {
                            var site = blockedSiteDAO.getAllNow().find {it.url.contains(currentUrl) }
                            auditSiteDAO.insert(AuditSite(siteId = site?.id ?: 0, timestamp = Date()))
                            Log.d(TAG, "New Audit Log { Site:${site?.siteName}, Url:${site?.url}, Timestamp:${Date()} }");
                        }
                    }

                    Log.d(TAG, "Is Monitored: $isMonitored")
                }
            }
        }
    }

    private fun extractUrl(node: AccessibilityNodeInfo?): String? {
        if (node == null) {
            return null
        }

        if (node.className == "android.widget.EditText" && node.text != null) {
            val text = node.text.toString()
            return text

        }

        for (i in 0 until node.childCount) {
            val childNode = node.getChild(i)
            val url = extractUrl(childNode)
            if (url != null) {
                return url
            }
        }
        return null
    }

    override fun onInterrupt() {
        Log.d(TAG, "Accessibility Service interrupted")
    }
}