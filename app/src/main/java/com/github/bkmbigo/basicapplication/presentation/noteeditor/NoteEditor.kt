package com.github.bkmbigo.basicapplication.presentation.noteeditor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.bkmbigo.basicapplication.R
import com.github.bkmbigo.basicapplication.databinding.FragmentNoteEditorBinding
import com.github.bkmbigo.basicapplication.domain.models.Note
import com.github.bkmbigo.basicapplication.domain.models.NotePriority
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteEditor : Fragment() {

    private var _binding: FragmentNoteEditorBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Fragment Note Editor not initialized")

    private val noteEditorViewModel by viewModels<NoteEditorViewModel>()
    private val priorityList = NotePriority.values().toList()
    private var prioritySelected: NotePriority? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            priorityList.map { it.name }
        )
        binding.actvPriority.setAdapter(adapter)
        binding.actvPriority.setOnItemClickListener { _, _, position, _ ->
            prioritySelected = priorityList[position]
        }

        binding.btSaveNote.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        val title = binding.tietTitle.text.toString()
        val description = binding.tietDescription.text.toString()
        if (title.isBlank() || description.isBlank() || prioritySelected == null) {
            Toast.makeText(
                requireContext(),
                "Fields Empty: ${
                    if (title.isBlank()) {
                        "Title "
                    } else {
                        ""
                    }
                } ${
                    if (description.isBlank()) {
                        "Description "
                    } else {
                        ""
                    }
                }${
                    if (prioritySelected == null) {
                        "Priority"
                    } else {
                        ""
                    }
                }",
                Toast.LENGTH_SHORT
            ).show()
            showInputErrors()
            return
        }
        resetInputErrors()
        lifecycleScope.launch(dispatcher) {
            noteEditorViewModel.saveNote(Note(0, title, description, prioritySelected!!))
        }
        findNavController().navigateUp()
    }

    private fun showInputErrors() {
        if (
            binding.tietTitle.text.toString().isBlank() ||
            binding.tietDescription.text.toString().isBlank() ||
            prioritySelected == null
        ) {
            if (binding.tietTitle.text.toString().isBlank()) {
                binding.tilTitle.error = getString(R.string.error_no_title)
                binding.tilTitle.isErrorEnabled = true
            } else {
                binding.tilTitle.isErrorEnabled = false
            }

            if (binding.tietDescription.text.toString().isBlank()) {
                binding.tilDescription.error = getString(R.string.error_no_description)
                binding.tilDescription.isErrorEnabled = true
            } else {
                binding.tilDescription.isErrorEnabled = false
            }

            if (prioritySelected == null) {
                binding.tilPriority.error = getString(R.string.error_no_description)
                binding.tilPriority.isErrorEnabled = true
            } else {
                binding.tilPriority.isErrorEnabled = false
            }
        } else resetInputErrors()
    }

    private fun resetInputErrors() {
        binding.tilTitle.isErrorEnabled = false
        binding.tilDescription.isErrorEnabled = false
        binding.tilPriority.isErrorEnabled = false
    }
}