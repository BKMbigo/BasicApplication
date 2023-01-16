package com.github.bkmbigo.basicapplication.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.bkmbigo.basicapplication.domain.models.Note
import com.github.bkmbigo.basicapplication.domain.models.NotePriority
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NetworkRepositoryMockTest {
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteRepositoryImpl: NoteRepositoryImpl

    @Before
    fun prepareMockDatabase() {
        noteDatabase = mockk()
        noteRepositoryImpl = NoteRepositoryImpl(noteDatabase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertNoteCall() = runTest {
        coEvery { noteDatabase.dao.insertNote(any()) } returns 1L

        val note = Note(0, "New Note", "This is a new note", NotePriority.NORMAL)
        noteRepositoryImpl.insertNote(note)

        coVerify { noteDatabase.dao.insertNote(note) }
    }
}