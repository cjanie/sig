package com.android.businesslogic.inmemoryadapters

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.gateways.PointCommandGateway

internal class InMemoryPointCommandGatewayImpl: PointCommandGateway {

    override fun save(point: Point): Long {
        return 1L
    }
}