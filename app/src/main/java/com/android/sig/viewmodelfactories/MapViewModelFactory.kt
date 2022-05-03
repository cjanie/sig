package com.android.sig.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.businesslogic.usecases.GetPointsUseCase
import com.android.sig.viewmodels.MapViewModel
import java.lang.IllegalArgumentException

class MapViewModelFactory(private val getPointsUseCase: GetPointsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MapViewModel::class.java))
            return MapViewModel(this.getPointsUseCase) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}