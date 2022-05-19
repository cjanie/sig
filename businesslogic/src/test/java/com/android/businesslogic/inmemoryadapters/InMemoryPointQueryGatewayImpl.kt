package com.android.businesslogic.inmemoryadapters

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.gateways.PointQueryGateway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class InMemoryPointQueryGatewayImpl: PointQueryGateway {

    private var _points: List<Point> = ArrayList()

    override fun getPoints(): Flow<List<Point>> {
        return flowOf(this._points)
    }

    fun setPoints(points: List<Point>) {
        this._points = points
    }
}