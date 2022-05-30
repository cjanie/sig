package com.android.sig.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.usecases.GetPointsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last

class MapViewModel(val getPointsUseCase: GetPointsUseCase): ViewModel() {

    // View model action : to run the use case
    private val _points: LiveData<List<Point>> =
        this.getPointsUseCase.handle()
        .asLiveData()

    // View model result from running the use case
    val points: LiveData<List<Point>> = _points

}