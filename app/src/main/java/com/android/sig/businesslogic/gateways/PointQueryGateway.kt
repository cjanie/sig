package com.android.sig.businesslogic.gateways

import com.android.sig.businesslogic.entities.Point

interface PointQueryGateway {

    fun getPoints(): List<Point>
}