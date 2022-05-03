package com.android.sig.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.usecases.GetPointsUseCase

class MapViewModel(val getPointsUseCase: GetPointsUseCase): ViewModel() {

    private val _points = MutableLiveData(
        this.getPointsUseCase.handle()
    )
    val points: LiveData<List<Point>> = _points

}