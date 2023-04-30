package com.example.tasks.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.models.Task
import com.example.tasks.models.TaskDao
import kotlinx.coroutines.launch

class TasksViewModel(val dao : TaskDao) : ViewModel() {
    var taskName = ""

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskDesc = taskName
            dao.insertTask(task)
        }
    }
}