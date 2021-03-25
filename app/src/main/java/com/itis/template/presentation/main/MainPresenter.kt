package com.itis.template.presentation.main

import com.itis.template.domain.FindCityUseCase
import com.itis.template.domain.LocationRepository
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val findCityUseCase: FindCityUseCase,
    private val locationRepository: LocationRepository
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onHelloClick() // fetching city(-ies) when user opens screen
    }

    fun onHelloClick() {
        presenterScope.launch {
            try {
                viewState.showLoading()
                findCityUseCase.findWeatherInCity("Kazan").run {
                    viewState.showWeather(main.temp.toString())
                }
            } catch (throwable: Throwable) {
                viewState.consumerError(throwable)
            } finally {
                viewState.hideLoading()
            }
        }
    }

    fun onLocationClick() {
        presenterScope.launch {
            locationRepository.getUserLocation().also {
                viewState.showUserLocation(it)
            }
        }
    }

}
