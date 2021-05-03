package com.itis.template.presentation.main

import androidx.annotation.VisibleForTesting
import com.itis.template.domain.LocationRepository
import com.itis.template.domain.WeatherInteractor
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val findCityUseCase: WeatherInteractor,
    private val locationRepository: LocationRepository
) : MvpPresenter<MainView>() {

    var cityId = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onHelloClick() // fetching city(-ies) when user opens screen
    }

    fun onHelloClick() {
        presenterScope.launch {
            try {
                viewState.showLoading()
                findCityUseCase.findWeatherInCity(getCityName(cityId)).run {
                    viewState.showWeather(main.temp.toString())
                }
            } catch (throwable: Throwable) {
                viewState.consumerError(throwable)
            } finally {
                onFinal()
                viewState.hideLoading()
            }
        }
    }

    @VisibleForTesting
    fun getCityName(test: Int) = when (test) {
        0 -> "Kazan"
        1 -> "Test"
        else -> "Error"
    }

    @VisibleForTesting
    fun onFinal() {
        onLocationClick()
    }

    fun onLocationClick() {
        presenterScope.launch {
            locationRepository.getUserLocation().also {
                viewState.showUserLocation(it)
            }
        }
    }

}
