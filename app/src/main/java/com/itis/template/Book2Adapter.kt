package com.itis.template

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class Book2Adapter(
    private val itemClickLambda: (Book) -> Unit
) : ListAdapter<Book, BookHolder>(object : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder =
        BookHolder.create(parent, itemClickLambda)

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<Book>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }
}
