package com.github.bkmbigo.basicapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.bkmbigo.basicapplication.data.dao.NoteDao
import com.github.bkmbigo.basicapplication.domain.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val dao: NoteDao
}