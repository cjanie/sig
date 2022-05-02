package com.android.sig.businesslogic.gateways

import com.android.sig.businesslogic.entities.Point

interface PointCommandGateway {
    fun save(point: Point): Long
}
