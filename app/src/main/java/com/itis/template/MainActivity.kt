package com.itis.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_desc.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var adapter: BookAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = BookAdapter(getDataSource()) { author, _, _ ->
            navigateToDesc(author)
        }

        rv_books.adapter = adapter

        fab.setOnClickListener {
            adapter?.add(Book("TEST", "Test"))
        }
        sr_main.setOnRefreshListener {
            Handler().postDelayed({
                Log.e("Ss", "refresh")
                sr_main.isRefreshing = false
            }, 4000) // value in milliseconds)
        }
    }

    private fun navigateToDesc(author: String) {
        startActivity(DescActivity.createIntent(this, author))
    }

    private fun getDataSource(): List<Book> = arrayListOf(
        Book("LotR", "Толкин"),
        Book("Gарии Potter", "На словах"),
        Book("Преступление", "Лев"),
        Book("Анна", "Толстой"),
        Book("Война", "Бредбери"),
        Book("Мир", "Оуэл"),
        Book("Три собаки", "Мэдисон"),
        Book("451 градус", "Оуэл"),
        Book("Флекс", "Артем"),
        Book("Air", "Сергей"),
        Book("Лупа", "Анек"),
        Book("Пупа", "Дот"),
        Book("Волк слабее льва и тигра", "Но в цирке не выступает"),
        Book("Не расстраивай меня", "РастроилРастроилРастроил"),
        Book("5 см в секунду", "Макото Рисуй облака Синкай"),
        Book("Нарусто", "Саске"),
        Book("Тоби Маквайер", "Лучший павук!!!"),
        Book("Матрица", "Вертолет Апачи"),
        Book("Суп", "Батин")

    )
}
