package com.itis.template.utils

import androidx.appcompat.widget.SearchView
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.kotlin.Flowables

fun SearchView.observeQuery() = Flowables.create<String>(source = {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            it.onNext(query)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            it.onNext(newText)
            return true
        }

    })
}, mode = BackpressureStrategy.LATEST)
