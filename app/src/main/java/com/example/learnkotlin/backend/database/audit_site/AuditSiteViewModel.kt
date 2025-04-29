package com.example.learnkotlin.backend.database.audit_site

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnkotlin.MainApplication
import com.example.learnkotlin.backend.database.schedule.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuditSiteViewModel : ViewModel() {
    private val auditSiteDao = MainApplication.appDatabase.auditSiteDAO

    val audits : LiveData<List<AuditSite>> = auditSiteDao.getAll()

    fun addAudit(auditSite: AuditSite){
        viewModelScope.launch(Dispatchers.IO){
            auditSiteDao.insert(auditSite)
        }
    }

    fun deleteAudit(auditSite: AuditSite){
        viewModelScope.launch(Dispatchers.IO) {
            auditSiteDao.delete(auditSite)
        }
    }

    fun updateAudit(auditSite: AuditSite){
        viewModelScope.launch(Dispatchers.IO) {
            auditSiteDao.update(auditSite)
        }
    }
}