package com.itis.template

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
    private var list: List<Book>,
    private val itemClick: (Book) -> Unit
) : RecyclerView.Adapter<BookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder =
        BookHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: BookHolder, position: Int) =
        holder.bind(list[position])

    override fun onBindViewHolder(holder: BookHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            (payloads[0] as? Bundle)?.also {
                holder.updateFields(it)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateDataSource(newList: List<Book>) {
//        notifyItemChanged(1, Bundle().putString("ARG_NAME", "Test"))
        val callback = BookDiffCallback(list, newList)
        val result = DiffUtil.calculateDiff(callback, true)
        result.dispatchUpdatesTo(this)
        list = newList
    }
}
