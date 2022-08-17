package com.lalosapps.todoapp.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    val description: String?,
    val done: Boolean,
    @PrimaryKey val id: Int? = null
)
