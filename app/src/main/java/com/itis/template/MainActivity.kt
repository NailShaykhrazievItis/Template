package com.itis.template

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
                Subject("Математика", "Описание математики", 1),
                Subject("Информатика", "Описание информатики", 2)
        )

        val myAdapter = MainAdapter(items, object : MainAdapter.Callback {
            override fun onItemClicked(item: Subject) {
                //TODO Сюда придёт элемент, по которому кликнули. Можно дальше с ним работать
            }
        })

        myRecycler.adapter = myAdapter
    }

    fun get(name: String) = resources.getIdentifier(name, "drawable", packageName)
}
