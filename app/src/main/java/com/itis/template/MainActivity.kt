package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
                Subject("Математика", "Описание математики", R.drawable.ic_launcher_background),
                Subject("Информатика", "Описание информатики", R.drawable.ic_launcher_background)
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
