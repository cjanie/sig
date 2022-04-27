package com.android.sig.adapters

import com.android.sig.businesslogic.entities.Point
import com.android.sig.businesslogic.gateways.SavePointGateway

internal class InMemorySavePointGateWayImpl: SavePointGateway {

    override fun save(point: Point): Long {
        return 1L
    }
}