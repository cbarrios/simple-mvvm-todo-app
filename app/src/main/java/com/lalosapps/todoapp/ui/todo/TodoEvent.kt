package com.lalosapps.todoapp.ui.todo

import com.lalosapps.todoapp.data.local.room.model.Todo

sealed class TodoEvent {

    data class OnTodoClick(val todo: Todo) : TodoEvent()

    data class OnDeleteTodoClick(val todo: Todo) : TodoEvent()

    data class OnDoneChange(val todo: Todo, val done: Boolean) : TodoEvent()

    object OnUndoDeleteClick : TodoEvent()

    object OnAddTodoClick : TodoEvent()

}
