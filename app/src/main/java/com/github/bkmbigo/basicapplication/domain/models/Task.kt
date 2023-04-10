package com.github.bkmbigo.basicapplication.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val id: Long,
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false
) {
    val titleForList: String
        get() = title.ifEmpty { description }

    val isActive: Boolean
        get() = !isCompleted

    val isEmpty: Boolean
        get() = title.isEmpty() || description.isEmpty()
}
