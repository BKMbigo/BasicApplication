package com.github.bkmbigo.basicapplication.presentation.noteeditor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.bkmbigo.basicapplication.databinding.FragmentNoteEditorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteEditor : Fragment() {

    private var _binding: FragmentNoteEditorBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Fragment Note Editor not initialized")

    private val noteEditorViewModel by viewModels<NoteEditorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actvPriority.setOnItemClickListener { _, _, position, _ ->
            //prioritySelected = priorityList[position]
        }
    }
}