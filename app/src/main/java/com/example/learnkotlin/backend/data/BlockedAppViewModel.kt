package com.example.learnkotlin.backend.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnkotlin.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BlockedAppViewModel(): ViewModel() {
    private val blockedAppDao = MainApplication.appDatabase.blockedAppDao

    val blockedApps : LiveData<List<BlockedApp>> = blockedAppDao.getAll()

    fun addBlockedApp(blockedApp: BlockedApp){
        viewModelScope.launch(Dispatchers.IO){
            blockedAppDao.insert(blockedApp)
        }
    }

    fun deleteBlockedApp(blockedApp: BlockedApp){
        viewModelScope.launch(Dispatchers.IO) {
            blockedAppDao.delete(blockedApp)
        }
    }

    fun updateBlockedApp(blockedApp: BlockedApp){
        viewModelScope.launch(Dispatchers.IO) {
            blockedAppDao.update((blockedApp))
        }
    }
}
