package com.example.exoplayersample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exoplayersample.databinding.ItemVerticalBinding

class VerticalListAdapter : ListAdapter<String, VerticalListAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: ItemVerticalBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.text = item
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemVerticalBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}