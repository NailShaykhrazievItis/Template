package com.itis.template

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class BookDiffCallback(
    private val oldList: List<Book>,
    private val newList: List<Book>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val bundle = Bundle().apply {
            if (oldList[oldItemPosition].name != newList[newItemPosition].name) {
                putString("ARG_NAME", newList[newItemPosition].name)
            }
            if (oldList[oldItemPosition].author != newList[newItemPosition].author) {
                putString("ARG_AUTHOR", newList[newItemPosition].author)
            }
        }
        return if (bundle.isEmpty) null else bundle
    }
}
