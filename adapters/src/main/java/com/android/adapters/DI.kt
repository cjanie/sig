package com.android.adapters

import com.android.adapters.adapters.PointCommandGatewayImpl
import com.android.adapters.adapters.PointQueryGatewayImpl
import com.android.businesslogic.gateways.PointCommandGateway
import com.android.businesslogic.gateways.PointQueryGateway

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