package com.example.tasks.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
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

    val tasks = dao.getAllTasks()
    val tasksString = tasks.map{
        tasks -> formatTasks(tasks)
    }

    private fun formatTasks(tasks : List<Task>) : String{
        var allTasks = ""
            for(taskNo in tasks.indices) {
                val individualTask = formatTask(tasks[taskNo])
                allTasks += individualTask + '\n'
            }
        return allTasks
    }

    private fun formatTask(task : Task) : String {
        return ("ID : ${task.taskId}" + '\n' + "Desc : ${task.taskDesc}" + '\n' + "Completed : ${task.taskDone}")
    }
}