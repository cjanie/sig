package com.android.sig.adapters

import com.android.sig.businesslogic.entities.Point
import com.android.sig.businesslogic.gateways.SavePointGateWay

internal class InMemorySavePointGateWayImpl: SavePointGateWay {

    override fun save(point: Point): Long {
        return 1L
    }
}