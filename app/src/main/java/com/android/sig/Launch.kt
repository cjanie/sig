package com.android.sig

import android.app.Application
import android.content.Context
import com.android.businesslogic.usecases.GetPointsUseCase
import com.android.businesslogic.usecases.SavePointUseCase
import com.android.infrastructure.DI
import com.android.infrastructure.adapters.PointCommandGatewayImpl
import com.android.infrastructure.database.LocalDatabase
import com.android.sig.viewmodelfactories.MapViewModelFactory
import com.android.sig.viewmodelfactories.SharedViewModelFactory

class Launch: Application() {

    private val di by lazy { DI(this) }

    val sharedViewModelFactory by lazy { SharedViewModelFactory(SavePointUseCase(this.di.pointCommandGateway)) }

    val mapViewModelFactory by lazy { MapViewModelFactory(GetPointsUseCase(this.di.pointQueryGateway)) }

}