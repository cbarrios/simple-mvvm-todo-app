package com.lalosapps.todoapp.data.repository

import com.lalosapps.todoapp.data.local.LocalTodoDataSource
import com.lalosapps.todoapp.data.local.room.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val localTodoDataSource: LocalTodoDataSource
) : TodoRepository {

    override suspend fun insertTodo(todo: Todo) {
        localTodoDataSource.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        localTodoDataSource.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? = localTodoDataSource.getTodoById(id)

    override fun getTodos(): Flow<List<Todo>> = localTodoDataSource.getTodos()
}