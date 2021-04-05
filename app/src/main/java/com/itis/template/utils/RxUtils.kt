package com.itis.template.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T> Single<T>.subscribeOnIoObserveOnMain() =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
