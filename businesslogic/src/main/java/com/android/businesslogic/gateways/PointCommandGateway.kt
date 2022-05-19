package com.android.businesslogic.gateways

import com.android.businesslogic.domain.entities.Point

interface PointCommandGateway {
    suspend fun save(point: Point): Long
}
