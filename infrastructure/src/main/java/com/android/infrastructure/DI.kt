package com.android.infrastructure

import com.android.businesslogic.gateways.PointCommandGateway
import com.android.businesslogic.gateways.PointQueryGateway
import com.android.infrastructure.adapters.PointCommandGatewayImpl
import com.android.infrastructure.adapters.PointQueryGatewayImpl

class DI {

    private val _pointCommandGateway: PointCommandGateway = PointCommandGatewayImpl()

    private val _pointQueryGateway: PointQueryGateway = PointQueryGatewayImpl()

    fun pointCommandGateway(): PointCommandGateway {
        return this._pointCommandGateway
    }

    fun pointQueryGateway(): PointQueryGateway {
        return this._pointQueryGateway
    }
}