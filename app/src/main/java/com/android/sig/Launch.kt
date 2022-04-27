package com.android.sig

import android.app.Application
import com.android.sig.adapters.SavePointGatewayImpl
import com.android.sig.businesslogic.gateways.SavePointGateway
import com.android.sig.businesslogic.usecases.SavePointUseCase
import com.android.sig.ui.SharedViewModelFactory

class Launch: Application() {

    private val _savePointGateway: SavePointGateway = SavePointGatewayImpl()

    private val _savePointUseCase: SavePointUseCase = SavePointUseCase(this._savePointGateway)

    private val _sharedViewModelFactory = SharedViewModelFactory(this._savePointUseCase)

    fun sharedViewModelFactory(): SharedViewModelFactory {
        return this._sharedViewModelFactory
    }
}