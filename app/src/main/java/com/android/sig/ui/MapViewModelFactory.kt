package com.android.sig.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.sig.businesslogic.usecases.GetPointsUseCase
import java.lang.IllegalArgumentException

class MapViewModelFactory(private val getPointsUseCase: GetPointsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MapViewModel::class.java))
            return MapViewModel(this.getPointsUseCase) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}