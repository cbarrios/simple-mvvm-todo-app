package com.lalosapps.todoapp.core.di

import android.content.Context
import androidx.room.Room
import com.lalosapps.todoapp.data.local.LocalTodoDataSource
import com.lalosapps.todoapp.data.local.room.dao.TodoDao
import com.lalosapps.todoapp.data.local.room.db.TodoDatabase
import com.lalosapps.todoapp.data.repository.TodoRepository
import com.lalosapps.todoapp.data.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(context, TodoDatabase::class.java, "todo_database").build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.todoDao
    }

    @Provides
    @Singleton
    fun provideTodoRepository(localTodoDataSource: LocalTodoDataSource): TodoRepository {
        return TodoRepositoryImpl(localTodoDataSource)
    }

}