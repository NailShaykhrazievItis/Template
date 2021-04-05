package com.itis.template.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.template.Main
import com.itis.template.WeatherResponse
import com.itis.template.data.LocationRepositoryImpl
import com.itis.template.domain.FindCityUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.launch

class MainViewModel(
    private val findCityUseCase: FindCityUseCase,
    private val locationRepository: LocationRepositoryImpl
) : ViewModel() {

    private val mProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val mMain: MutableLiveData<Main> = MutableLiveData()
    private val mMainRx: MutableLiveData<Result<Main>> = MutableLiveData()


    fun progress(): LiveData<Boolean> = mProgress

    fun mainTemp(): LiveData<Main> = mMain

    fun mainTempRx(): LiveData<Result<Main>> = mMainRx

    fun onHelloClick() {
        Singles.zip(
            findCityUseCase.findWeatherInCity("Kazan"),
            findCityUseCase.findWeatherInCity("Kazan"),
            findCityUseCase.findWeatherInCity("Kazan"),
        ).subscribeBy(onSuccess = { (one, two, t)->
            Log.e("1", one.toString())
            Log.e("2", two.toString())
        }, onError = {

        })

        findCityUseCase.findWeatherInCity("Kazan")
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mProgress.value = true }
            .doAfterTerminate { mProgress.value = false }
            .subscribeBy(onSuccess = {
                mMainRx.value = Result.success(it.main)
            }, onError = {
                mMainRx.value = Result.failure(it)
            })
    }

    fun onHelloClickSuspend() {
        viewModelScope.launch {
            try {
                mProgress.value = true

                findCityUseCase.findWeatherInCitySusp("Kazan").run {
//                    mMain.value = main
                }
            } catch (throwable: Throwable) {
//                 mMain.value = it.main
            } finally {
                mProgress.value = false
            }
        }
    }

    fun onMain(response: WeatherResponse): WeatherResponse = response

    fun onLocationClick() {
//        presenterScope.launch {
//            locationRepository.getUserLocation().also {
//                viewState.showUserLocation(it)
//            }
//        }
    }

}
