package com.itis.template

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
    private var dataSource: List<Book>,
    private val clickLambda: (String, Int, Book) -> Unit
) : RecyclerView.Adapter<BookItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemHolder =
        BookItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: BookItemHolder, position: Int) =
        holder.bind(dataSource[position])

    fun add(book: Book) {
        val temp = dataSource.toMutableList()
        temp.add(4, book)
        temp.add(4, book.apply { title += " 2"})
        temp.add(4, book.apply { title += " 3"})
        dataSource = temp
        notifyItemRangeChanged(4, 4)
    }
}
