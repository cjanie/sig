package com.android.sig.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sig.businesslogic.entities.Point
import com.android.sig.businesslogic.usecases.GetPointsUseCase

class MapViewModel(val getPointsUseCase: GetPointsUseCase): ViewModel() {

    private val _points = MutableLiveData(
        this.getPointsUseCase.handle()
    )
    val points: LiveData<List<Point>> = _points

}