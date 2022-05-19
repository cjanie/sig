package com.android.businesslogic.gateways

import com.android.businesslogic.domain.entities.Point
import kotlinx.coroutines.flow.Flow

interface PointQueryGateway {

    fun getPoints(): Flow<List<Point>>
}