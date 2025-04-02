package com.example.learnkotlin

import android.app.Application
import androidx.room.Room
import com.example.learnkotlin.backend.database.AppDatabase

class MainApplication :Application() {

    companion object{
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate(){
        super.onCreate()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.Name
        ).build()
    }
}