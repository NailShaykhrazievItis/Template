package com.itis.template

object BookRepository {

    fun getBooks() = arrayListOf(
        Book("Gena", "Cheburashka"),
        Book("Cheburashka", "Gena"),
        Book("Cheburashka", "Gena"),
        Book("Cheburashka", "Gena"),
        Book("Gennadiy", "Crocodile")
    )
}
