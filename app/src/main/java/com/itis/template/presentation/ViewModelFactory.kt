package com.itis.template.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itis.template.data.LocationRepositoryImpl
import com.itis.template.domain.FindCityUseCase
import com.itis.template.presentation.main.MainViewModel

class ViewModelFactory(
    private val findCityUseCase: FindCityUseCase,
    private val locationRepository: LocationRepositoryImpl
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(findCityUseCase, locationRepository) as? T
                    ?: throw IllegalArgumentException("Unknown ViewModel class")
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

}
