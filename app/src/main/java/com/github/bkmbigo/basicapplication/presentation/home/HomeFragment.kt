package com.github.bkmbigo.basicapplication.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.github.bkmbigo.basicapplication.R
import com.github.bkmbigo.basicapplication.databinding.FragmentHomeBinding
import com.github.bkmbigo.basicapplication.presentation.adapters.NoteAdapter
import com.github.bkmbigo.basicapplication.presentation.adapters.NoteAdapterItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Fragment Home Binding is not initialized")

    @Inject
    lateinit var noteAdapter: NoteAdapter

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        collectState()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = noteAdapter

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_noteEditor)
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.noteList.collect { list ->
                    val adapterList = list.map { note ->

                    }
                }
            }
        }
    }
}