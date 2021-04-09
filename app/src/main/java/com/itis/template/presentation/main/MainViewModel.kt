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
import com.itis.template.domain.SearchInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(
    private val findCityUseCase: FindCityUseCase,
    private val locationRepository: LocationRepositoryImpl,
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val mProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val mMain: MutableLiveData<Main> = MutableLiveData()
    private val mMainRx: MutableLiveData<Result<Main>> = MutableLiveData()
    private val mSearchResult: MutableLiveData<String> = MutableLiveData()

    private var weatherDisposable: Disposable? = null
    private val disposables = CompositeDisposable()

    init {
        findCityUseCase.userIdSubject
            .observeOn(Schedulers.io())
            .map { locationRepository.getUserLocationSingle(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {

            }, onError = {

            })
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        weatherDisposable?.dispose()
    }

    fun onSearch(query: String) {
        Log.e("ViewModel", "Query: $query")
        searchInteractor.search(query)?.also {
            mSearchResult.value = it
        }
    }

    fun search(): LiveData<String> = mSearchResult

    fun progress(): LiveData<Boolean> = mProgress

    fun mainTemp(): LiveData<Main> = mMain

    fun mainTempRx(): LiveData<Result<Main>> = mMainRx

    fun onHelloClick() {
        disposables += Singles.zip(
            findCityUseCase.findWeatherInCity("Kazan"),
            findCityUseCase.findWeatherInCity("Kazan"),
            findCityUseCase.findWeatherInCity("Kazan"),
        ).subscribeBy(onSuccess = { (one, two, t) ->
            Log.e("1", one.toString())
            Log.e("2", two.toString())
        }, onError = {

        })

        disposables += Observable.just("First", "Second")
            .subscribeBy(onComplete = {

            }, onError = {

            })

        if (weatherDisposable?.isDisposed != false) {
            weatherDisposable = findCityUseCase.findWeatherInCity("Kazan")
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mProgress.value = true }
                .doAfterTerminate { mProgress.value = false }
                .subscribeBy(onSuccess = {
                    mMainRx.value = Result.success(it.main)
                }, onError = {
                    mMainRx.value = Result.failure(it)
                })
        }
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
