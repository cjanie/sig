package com.android.sig

import android.app.Application
import com.android.adapters.adapters.PointCommandGatewayImpl
import com.android.adapters.adapters.PointQueryGatewayImpl
import com.android.businesslogic.gateways.PointCommandGateway
import com.android.businesslogic.gateways.PointQueryGateway
import com.android.businesslogic.usecases.GetPointsUseCase
import com.android.businesslogic.usecases.SavePointUseCase
import com.android.sig.viewmodelfactories.MapViewModelFactory
import com.android.sig.viewmodelfactories.SharedViewModelFactory

class Launch: Application() {

    private val _pointCommandGateway: PointCommandGateway = PointCommandGatewayImpl()

    private val _pointQueryGateway: PointQueryGateway = PointQueryGatewayImpl()

    private val _savePointUseCase: SavePointUseCase = SavePointUseCase(this._pointCommandGateway)

    private val _getPointsUseCase: GetPointsUseCase = GetPointsUseCase(this._pointQueryGateway)

    private val _sharedViewModelFactory = SharedViewModelFactory(this._savePointUseCase)

    private val _mapViewModelFactory = MapViewModelFactory(this._getPointsUseCase)

    fun sharedViewModelFactory(): SharedViewModelFactory {
        return this._sharedViewModelFactory
    }

    fun mapViewModelFactory(): MapViewModelFactory {
        return this._mapViewModelFactory
    }
}