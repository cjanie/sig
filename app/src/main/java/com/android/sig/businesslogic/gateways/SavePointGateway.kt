package com.android.sig.businesslogic.gateways

import com.android.sig.businesslogic.entities.Point

interface SavePointGateWay {
    fun save(point: Point): Long
}
