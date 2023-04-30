package com.example.tasks.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao : TaskDao

    companion object {
        @Volatile // used in multi-threading env, this variable will be updated with all threads.
        private var INSTANCE : TaskDatabase ?= null

        // get instance of TaskDatabase class
        fun getInstance(context : Context) : TaskDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,"task_database")
                        .build()
                }
                return instance
            }
        }
    }
}