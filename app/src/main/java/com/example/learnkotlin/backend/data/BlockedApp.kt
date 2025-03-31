package com.example.learnkotlin.backend.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BlockedApp (
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "app_name") val appName: String,
    @ColumnInfo(name = "is_blocked") var isBlocked: Boolean
)