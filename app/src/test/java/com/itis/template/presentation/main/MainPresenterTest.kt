package com.itis.template.presentation.main

import com.itis.template.domain.LocationRepository
import com.itis.template.domain.WeatherInteractor
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MainPresenterTest {

    @MockK
    lateinit var findCityUseCase: WeatherInteractor

    @MockK
    lateinit var locationRepository: LocationRepository

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `MainView$$State`

    private lateinit var presenter: MainPresenter

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeEach
    fun setUp() {
        presenter = spyk(MainPresenter(findCityUseCase, locationRepository))
        presenter.setViewState(viewState)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    @DisplayName("При загрузке ожидаем блаблабал")
    fun onHelloClick() {
        // Arrange
        val expectedTemp = 32.0
        val expectedId = 1
        val expectedCityName = "Blalalads"

        every { presenter.getCityName(expectedId) } returns expectedCityName
        coEvery { findCityUseCase.findWeatherInCity(expectedCityName) } returns mockk {
            every { main } returns mockk {
                every { temp } returns expectedTemp
            }
        }
        every { presenter.onFinal() } just Runs
        presenter.cityId = expectedId

        // Act
        presenter.onHelloClick()

        // Assert
        verify {
            viewState.showLoading()
            viewState.showWeather(expectedTemp.toString())
            viewState.hideLoading()
            presenter.onFinal()
        }
        verify(inverse = true) {
            viewState.consumerError(any())
        }
    }

    @Test
    @DisplayName("При загрузке ожидаем ошибку")
    fun onHelloClickExpectedError() {
        // Arrange
        val expectedTemp = 32.0
        val expectedId = 1
        val expectedCityName = "Blalalads"
        val expectedError = mockk<Throwable>()

        coEvery { findCityUseCase.findWeatherInCity(any()) } throws expectedError
        every { presenter.onFinal() } just Runs
        presenter.cityId = expectedId

        // Act
        presenter.onHelloClick()

        // Assert
        verify {
            viewState.showLoading()
            viewState.consumerError(expectedError)
            viewState.hideLoading()
            presenter.onFinal()
        }
        verify(inverse = true) {
            viewState.showWeather(expectedTemp.toString())
        }
    }

    inner class EnumJan() {


    }

    inner class EnumFeb() {


    }
}
