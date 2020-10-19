package com.itis.template

object BookRepository {

    fun getBooks() = arrayListOf(
        Book(1,"Gena", "Cheburashka"),
        Book(2,"Cheburashka", "Gena"),
        Book(3,"Cheburashka", "Gena"),
        Book(4,"Cheburashka", "Gena"),
        Book(5,"Gennadiy", "Crocodile")
    )

    fun getBooks2() = arrayListOf(
        Book(1,"Cheburashka", "Cheburashka"),
        Book(2,"Gena", "Gena"),
        Book(3,"Cheburashka", "Gena"),
        Book(4,"Cheburashka", "Gena"),
        Book(5,"Gennadiy", "Crocodile")
    )
}
