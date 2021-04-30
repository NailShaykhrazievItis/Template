package com.itis.template.presentation.main

import com.itis.template.data.LocationRepositoryImpl
import com.itis.template.domain.FindCityUseCase
import com.itis.template.presentation.Screens
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import ru.terrakok.cicerone.Router

class MainPresenter(
    private val findCityUseCase: FindCityUseCase,
    private val locationRepository: LocationRepositoryImpl,
    private val router: Router
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

    fun onNextClick() {
        router.navigateTo(Screens.LoginScreen)
    }

    fun onLocationClick() {
        presenterScope.launch {
            locationRepository.getUserLocation().also {
                viewState.showUserLocation(it)
            }
        }
    }

}
