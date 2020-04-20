package com.itis.template.ui

import com.itis.template.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val repository: WeatherRepository
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        fetchWeather()
    }

    fun onHelloClick() {
        viewState.setResult("HeLLo general kenobi")
    }

    private fun fetchWeather() {
        presenterScope.launch {
            viewState.showLoading()
            delay(1500)
            try {
                withContext(Dispatchers.IO) {
                    repository.fetch("Moscow")
                }.let {
                    viewState.setResult(it.main.toString())
                }
            } catch (ex: Throwable) {
                viewState.showError(ex)
            } finally {
                viewState.hideLoading()
            }
        }
    }

    fun onPermissionCheck(result: Boolean) {
        if (!result) {
            viewState.requestPermissions()
        } else {
            // request
        }
    }
}
