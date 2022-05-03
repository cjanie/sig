package com.android.businesslogic.inmemoryadapters

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.gateways.PointQueryGateway

class InMemoryPointQueryGatewayImpl: PointQueryGateway {

    private var _points: List<Point> = ArrayList()

    override fun getPoints(): List<Point> {
        return this._points
    }

    fun setPoints(points: List<Point>) {
        this._points = points
    }
}