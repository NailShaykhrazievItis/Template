package com.itis.template.domain

class SearchInteractor {

    private val list = arrayListOf("Test", "Nail")

    fun search(query: String): String? = list.find { it == query }
}
