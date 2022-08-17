package com.lalosapps.todoapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalosapps.todoapp.core.util.Routes
import com.lalosapps.todoapp.core.util.UiEvent
import com.lalosapps.todoapp.data.local.room.model.Todo
import com.lalosapps.todoapp.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todos = todoRepository.getTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onEvent(event: TodoEvent) {
        when (event) {
            is TodoEvent.OnTodoClick -> sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            is TodoEvent.OnDeleteTodoClick -> deleteTodo(event)
            is TodoEvent.OnDoneChange -> modifyTodoDone(event)
            is TodoEvent.OnUndoDeleteClick -> undoDeletedTodo()
            is TodoEvent.OnAddTodoClick -> sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private fun deleteTodo(event: TodoEvent.OnDeleteTodoClick) {
        viewModelScope.launch {
            deletedTodo = event.todo
            todoRepository.deleteTodo(event.todo)
            sendUiEvent(UiEvent.ShowSnackbar("Todo deleted", "Undo"))
        }
    }

    private fun modifyTodoDone(event: TodoEvent.OnDoneChange) {
        viewModelScope.launch {
            todoRepository.insertTodo(event.todo.copy(done = event.done))
        }
    }

    private fun undoDeletedTodo() {
        deletedTodo?.let { todo ->
            viewModelScope.launch {
                todoRepository.insertTodo(todo)
            }
        }
    }

}