package com.android.adapters.adapters

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.gateways.PointCommandGateway

internal class PointCommandGatewayImpl: PointCommandGateway {

    override fun save(point: Point): Long {
        return 1L // TODO
    }
}