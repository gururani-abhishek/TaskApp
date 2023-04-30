package com.example.tasks.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task : Task)

    @Update
    suspend fun updateTask(task : Task)

    @Delete
    suspend fun deleteTask(task : Task)

    @Query("select * from task_table where task_id = :taskId")
    fun getTask(taskId : Long) : LiveData<Task>

    @Query("select * from task_table order by task_id desc")
    fun getAllTasks() : LiveData<List<Task>>

}