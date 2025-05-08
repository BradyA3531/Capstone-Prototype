package com.example.learnkotlin.backend.database.audit_site

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.learnkotlin.backend.database.DateConverter
import com.example.learnkotlin.backend.database.blocked_app.BlockedApp
import com.example.learnkotlin.backend.database.blocked_site.BlockedSite
import java.util.Date

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = BlockedSite::class,
            parentColumns = ["id"],
            childColumns = ["site_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class AuditSite (
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "site_id") val siteId: Int,
    @ColumnInfo(name = "timestamp") val timestamp: Date,
)