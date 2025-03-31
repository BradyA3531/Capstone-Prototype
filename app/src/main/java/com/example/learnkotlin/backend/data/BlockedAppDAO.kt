package com.example.learnkotlin.backend.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BlockedAppDAO {
    @Query("SELECT * FROM blockedapp")
    fun getAll(): LiveData<List<BlockedApp>>

    @Insert
    suspend fun insert(blockedApp: BlockedApp)

    @Delete
    suspend fun delete(blockedApp: BlockedApp)

    @Update
    suspend fun update(blockedApp: BlockedApp)
}