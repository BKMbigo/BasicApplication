package com.github.bkmbigo.basicapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.bkmbigo.basicapplication.databinding.ItemNoteBinding
import javax.inject.Inject

class NoteAdapter @Inject constructor() :
    ListAdapter<NoteAdapterItem, NoteAdapter.NoteViewHolder>(NoteDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NoteViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        fun bind(note: NoteAdapterItem) {
            binding.tvNoteTitle.text = note.note.title
            //binding.tvNotePriority.text = note.note.priority.name
            binding.tvNoteDescription.text = note.note.description
            binding.root.setOnClickListener {
                note.onItemClicked(note.note)
            }
        }
    }

    class NoteDiffUtil : DiffUtil.ItemCallback<NoteAdapterItem>() {
        override fun areItemsTheSame(oldItem: NoteAdapterItem, newItem: NoteAdapterItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NoteAdapterItem,
            newItem: NoteAdapterItem
        ): Boolean {
            return oldItem.note.hashCode() == newItem.note.hashCode()
        }
    }
}