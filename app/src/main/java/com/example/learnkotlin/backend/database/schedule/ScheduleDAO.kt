package com.example.learnkotlin.backend.database.schedule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ScheduleDAO {
    @Query("SELECT * FROM schedule")
    fun getAll(): LiveData<List<Schedule>>

    @Insert
    suspend fun insert(schedule: Schedule)

    @Delete
    suspend fun delete(schedule: Schedule)

    @Update
    suspend fun update(schedule: Schedule)
}