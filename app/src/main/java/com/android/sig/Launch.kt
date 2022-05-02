package com.android.sig

import android.app.Application
import com.android.sig.adapters.PointCommandGatewayImpl
import com.android.sig.businesslogic.gateways.PointCommandGateway
import com.android.sig.businesslogic.usecases.SavePointUseCase
import com.android.sig.ui.SharedViewModelFactory

class Launch: Application() {

    private val _pointCommandGateway: PointCommandGateway = PointCommandGatewayImpl()

    private val _savePointUseCase: SavePointUseCase = SavePointUseCase(this._pointCommandGateway)

    private val _sharedViewModelFactory = SharedViewModelFactory(this._savePointUseCase)

    fun sharedViewModelFactory(): SharedViewModelFactory {
        return this._sharedViewModelFactory
    }
}