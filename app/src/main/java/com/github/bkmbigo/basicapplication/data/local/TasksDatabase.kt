package com.github.bkmbigo.basicapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.bkmbigo.basicapplication.domain.models.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {
    abstract val tasksDao: TasksDao
}