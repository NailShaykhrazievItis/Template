package com.itis.template.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.template.Main
import com.itis.template.data.LocationRepositoryImpl
import com.itis.template.domain.FindCityUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val findCityUseCase: FindCityUseCase,
    private val locationRepository: LocationRepositoryImpl
) : ViewModel() {

    private val mProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val mMain: MutableLiveData<Main> = MutableLiveData()

    fun progress(): LiveData<Boolean> = mProgress

    fun mainTemp(): LiveData<Main> = mMain

    fun onHelloClick() {
        viewModelScope.launch {
            try {
                mProgress.value = true
                findCityUseCase.findWeatherInCity("Kazan").run {
                    mMain.value =main
                }
            } catch (throwable: Throwable) {
//                mMain.value = throwable
            } finally {
                mProgress.value = false
            }
        }
    }

    fun onLocationClick() {
//        presenterScope.launch {
//            locationRepository.getUserLocation().also {
//                viewState.showUserLocation(it)
//            }
//        }
    }

}
