package com.android.businesslogic.usecases

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.gateways.PointQueryGateway
import kotlinx.coroutines.flow.Flow

class GetPointsUseCase(val pointQueryGateway: PointQueryGateway) {

    fun handle(): Flow<List<Point>> {
        return this.pointQueryGateway.getPoints()
    }

}