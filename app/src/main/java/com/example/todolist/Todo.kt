package com.example.todolist

import android.text.Editable

data class Todo(
    val title: Editable,
    var isChecked:  Boolean = false
)