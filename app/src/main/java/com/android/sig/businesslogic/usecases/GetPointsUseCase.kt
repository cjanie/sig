package com.android.sig.businesslogic.usecases

import com.android.sig.businesslogic.entities.Point
import com.android.sig.businesslogic.gateways.PointQueryGateway

class GetPointsUseCase(val pointQueryGateway: PointQueryGateway) {

    fun handle(): List<Point> {
        return this.pointQueryGateway.getPoints()
    }
}