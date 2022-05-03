package com.android.businesslogic.gateways

import com.android.businesslogic.domain.entities.Point

interface PointQueryGateway {

    fun getPoints(): List<Point>
}