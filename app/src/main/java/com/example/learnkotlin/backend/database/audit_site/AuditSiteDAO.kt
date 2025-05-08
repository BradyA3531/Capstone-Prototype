package com.example.learnkotlin.backend.database.audit_site

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface AuditSiteDAO {
    @Query("SELECT * FROM auditsite")
    fun getAll(): LiveData<List<AuditSite>>

    @Query("SELECT * FROM auditsite")
    suspend fun getAllNow(): List<AuditSite>

    @Insert
    suspend fun insert(auditSite: AuditSite)

    @Delete
    suspend fun delete(auditSite: AuditSite)

    @Update
    suspend fun update(auditSite: AuditSite)
}