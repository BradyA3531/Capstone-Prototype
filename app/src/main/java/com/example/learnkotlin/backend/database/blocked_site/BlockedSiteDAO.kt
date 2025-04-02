package com.example.learnkotlin.backend.database.blocked_site

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BlockedSiteDAO {
    @Query("SELECT * FROM blockedsite")
    fun getAll(): LiveData<List<BlockedSite>>

    @Insert
    suspend fun insert(blockedSite: BlockedSite)

    @Delete
    suspend fun delete(blockedSite: BlockedSite)

    @Update
    suspend fun update(blockedSite: BlockedSite)
}