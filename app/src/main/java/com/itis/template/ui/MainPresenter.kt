package com.itis.template.ui

import com.itis.template.repository.WeatherRepository
import kotlinx.coroutines.*

class MainPresenter(
    private var view: MainView?,
    private val repository: WeatherRepository
) : CoroutineScope by MainScope() {

    fun onHelloClick() {
        view?.setResult("HeLLo general kenobi")
    }

    fun fetchWeather() {
        launch {
            view?.showLoading()
            delay(1500)
            try {
                withContext(Dispatchers.IO) {
                    repository.fetch("Moscow")
                }.let {
                    view?.setResult(it.main.toString())
                }
            } catch (ex: Throwable) {
                view?.showError(ex)
            } finally {
                view?.hideLoading()
            }
        }
    }

    fun onPermissionCheck(result: Boolean) {
        if (!result) {
            view?.requestPermissions()
        } else {
            // request
        }
    }

    fun clearData() {
        coroutineContext.cancelChildren()
        view = null
    }
}
