package com.example.learnkotlin.backend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.learnkotlin.backend.database.audit_site.AuditSite
import com.example.learnkotlin.backend.database.audit_site.AuditSiteDAO
import com.example.learnkotlin.backend.database.blocked_app.BlockedApp
import com.example.learnkotlin.backend.database.blocked_app.BlockedAppDAO
import com.example.learnkotlin.backend.database.blocked_site.BlockedSiteDAO
import com.example.learnkotlin.backend.database.blocked_site.BlockedSite
import com.example.learnkotlin.backend.database.schedule.Schedule
import com.example.learnkotlin.backend.database.schedule.ScheduleDAO

@Database(entities = [BlockedApp::class, BlockedSite::class, Schedule::class, AuditSite::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val blockedAppDao: BlockedAppDAO
    abstract val blockedSiteDao: BlockedSiteDAO
    abstract val scheduleDAO: ScheduleDAO
    abstract val auditSiteDAO: AuditSiteDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_blocker_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}