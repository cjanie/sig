package com.android.sig.businesslogic.gateways

import com.android.sig.businesslogic.entities.Point

interface SavePointGateway {
    fun save(point: Point): Long
}
