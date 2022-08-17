package com.lalosapps.todoapp.data.local

import com.lalosapps.todoapp.data.local.room.dao.TodoDao
import com.lalosapps.todoapp.data.local.room.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalTodoDataSource @Inject constructor(private val dao: TodoDao) {

    suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    suspend fun getTodoById(id: Int): Todo? = dao.getTodoById(id)

    fun getTodos(): Flow<List<Todo>> = dao.getTodos()
}