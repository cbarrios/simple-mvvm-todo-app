package com.lalosapps.todoapp.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lalosapps.todoapp.data.local.room.dao.TodoDao
import com.lalosapps.todoapp.data.local.room.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract val todoDao: TodoDao
}