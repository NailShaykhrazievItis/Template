package com.itis.template

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: BookAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = BookAdapter(
            BookRepository.getBooks()
        ) {
            Toast.makeText(this, "hi $it", Toast.LENGTH_SHORT).show()
        }
        rv_book.adapter = adapter
        rv_book.addItemDecoration(SpaceItemDecoration(this))

        swipe.setOnRefreshListener {
            adapter?.updateDataSource(arrayListOf(Book(1, "test", "amazing")))
            swipe.isRefreshing = false
        }
        fab_add.setOnClickListener {
            SignInDialog.show(supportFragmentManager, positiveAction = { name, pass ->
                Snackbar.make(it, "Success data from dialog $name", Snackbar.LENGTH_LONG).show()
            }, negativeAction = {
                Snackbar.make(it, "I am хлебушек", Snackbar.LENGTH_SHORT).show()
            })
        }
    }
}
