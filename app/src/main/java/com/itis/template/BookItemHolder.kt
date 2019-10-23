package com.itis.template

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_book.*

class BookItemHolder(
    override val containerView: View,
    private val clickLambda: (String, Int, Book) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(book: Book) {
        tv_title.text = book.title
        tv_author.text = book.author

        itemView.setOnClickListener {
            clickLambda(book.author, 62, book)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (String, Int, Book) -> Unit) =
            BookItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false),
                clickLambda
            )
    }
}
