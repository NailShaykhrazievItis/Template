package com.itis.template

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_book.*

class BookHolder(
    override val containerView: View,
    private val itemClick: (Book) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var book: Book? = null

    init {
        itemView.setOnClickListener {
            book?.also(itemClick)
        }
    }

    fun bind(book: Book) {
        this.book = book
        with(book) {
            tv_name.text = name
            tv_desc.text = author
        }
    }

    fun updateFields(bundle: Bundle) {
        if (bundle.containsKey("ARG_NAME")) {
            bundle.getString("ARG_NAME").also {
                Log.e("HOLDER", "ARG_NAME $it")
                tv_name.text = it
            }
        }
        if (bundle.containsKey("ARG_AUTHOR")) {
            bundle.getString("ARG_AUTHOR").also {
                Log.e("HOLDER", "ARG_AUTHOR $it")
                tv_desc.text = it
            }
        }
    }

    companion object {

        fun create(parent: ViewGroup, itemClick: (Book) -> Unit): BookHolder =
            BookHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false),
                itemClick
            )

    }
}
