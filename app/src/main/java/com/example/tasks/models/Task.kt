package com.example.tasks.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task (

    @ColumnInfo(name = "task_name") var taskDesc : String = "",

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "task_id") var taskId : Long = 0L,

    @ColumnInfo(name = "task_done") var taskDone : Boolean = false
)