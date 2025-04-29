package com.example.learnkotlin.backend.database.blocked_site

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnkotlin.MainApplication
import com.example.learnkotlin.backend.database.blocked_app.BlockedApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BlockedSiteViewModel(): ViewModel() {
    private val blockedSiteDao = MainApplication.appDatabase.blockedSiteDao

    val blockedSites : LiveData<List<BlockedSite>> = blockedSiteDao.getAll()

    suspend fun getById(id: Int): BlockedSite? = withContext(Dispatchers.IO){
        blockedSiteDao.getById(id)
    }

    fun addBlockedSite(blockedSite: BlockedSite){
        viewModelScope.launch(Dispatchers.IO){
            blockedSiteDao.insert(blockedSite)
        }
    }

    fun deleteBlockedSite(blockedSite: BlockedSite){
        viewModelScope.launch(Dispatchers.IO) {
            blockedSiteDao.delete(blockedSite)
        }
    }

    fun updateBlockedSite(blockedSite: BlockedSite){
        viewModelScope.launch(Dispatchers.IO) {
            blockedSiteDao.update((blockedSite))
        }
    }
}