package com.example.learnkotlin.backend.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BlockedApp::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val blockedAppDao: BlockedAppDAO
    companion object{
        const val Name = "BlockedApp_DB"
    }
}