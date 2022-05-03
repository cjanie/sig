package com.android.businesslogic.usecases

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.gateways.PointQueryGateway

class GetPointsUseCase(val pointQueryGateway: PointQueryGateway) {

    fun handle(): List<Point> {
        return this.pointQueryGateway.getPoints()
    }
}