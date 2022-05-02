package com.android.sig.adapters

import com.android.sig.businesslogic.entities.Point
import com.android.sig.businesslogic.gateways.PointCommandGateway

internal class InMemoryPointCommandGatewayImpl: PointCommandGateway {

    override fun save(point: Point): Long {
        return 1L
    }
}