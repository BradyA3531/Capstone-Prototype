package com.example.learnkotlin.backend.database.blocked_site

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BlockedSite (
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "site_name") val siteName: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "is_blocked") var isBlocked: Boolean
)