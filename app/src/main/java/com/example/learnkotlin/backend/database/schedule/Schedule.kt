package com.example.learnkotlin.backend.database.schedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.learnkotlin.backend.database.blocked_app.BlockedApp
import com.example.learnkotlin.backend.database.blocked_site.BlockedSite

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = BlockedApp::class,
            parentColumns = ["id"],
            childColumns = ["app_id"],
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = BlockedSite::class,
            parentColumns = ["id"],
            childColumns =  ["site_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Schedule(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "start_time") val startTime: String,
    @ColumnInfo(name = "end_time") val endTime: String,
    @ColumnInfo(name = "app_id") val appId: Int?,
    @ColumnInfo(name = "site_id") val siteId: Int?,
)

