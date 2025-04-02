package com.example.learnkotlin.backend.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learnkotlin.backend.database.blocked_app.BlockedApp
import com.example.learnkotlin.backend.database.blocked_app.BlockedAppDAO
import com.example.learnkotlin.backend.database.blocked_site.BlockedSiteDAO
import com.example.learnkotlin.backend.database.blocked_site.BlockedSite
import com.example.learnkotlin.backend.database.schedule.Schedule
import com.example.learnkotlin.backend.database.schedule.ScheduleDAO

@Database(entities = [BlockedApp::class, BlockedSite::class, Schedule::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val blockedAppDao: BlockedAppDAO
    abstract val blockedSiteDao: BlockedSiteDAO
    abstract val scheduleDAO: ScheduleDAO
    companion object{
        const val Name = "db"
    }
}