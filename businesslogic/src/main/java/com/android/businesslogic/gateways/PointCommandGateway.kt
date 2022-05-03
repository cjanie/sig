package com.android.businesslogic.gateways

import com.android.businesslogic.domain.entities.Point

interface PointCommandGateway {
    fun save(point: Point): Long
}
