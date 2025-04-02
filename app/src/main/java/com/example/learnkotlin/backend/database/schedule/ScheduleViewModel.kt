package com.example.learnkotlin.backend.database.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnkotlin.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleViewModel(): ViewModel() {
    private val scheduleDao = MainApplication.appDatabase.scheduleDAO

    val schedules : LiveData<List<Schedule>> = scheduleDao.getAll()

    fun addSchedule(schedule: Schedule){
        viewModelScope.launch(Dispatchers.IO){
            scheduleDao.insert(schedule)
        }
    }

    fun deleteSchedule(schedule: Schedule){
        viewModelScope.launch(Dispatchers.IO) {
            scheduleDao.delete(schedule)
        }
    }

    fun updateSchedule(schedule: Schedule){
        viewModelScope.launch(Dispatchers.IO) {
            scheduleDao.update(schedule)
        }
    }
}