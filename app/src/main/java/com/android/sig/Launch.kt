package com.android.sig

import android.app.Application
import com.android.businesslogic.usecases.GetPointsUseCase
import com.android.businesslogic.usecases.SavePointUseCase
import com.android.infrastructure.DI
import com.android.sig.viewmodelfactories.MapViewModelFactory
import com.android.sig.viewmodelfactories.SharedViewModelFactory

class Launch: Application() {

    private val di by lazy { DI(this) }

    val sharedViewModelFactory by lazy { SharedViewModelFactory(SavePointUseCase(this.di.pointCommandGateway())) }

    val mapViewModelFactory by lazy { MapViewModelFactory(GetPointsUseCase(this.di.pointQueryGateway())) }

}