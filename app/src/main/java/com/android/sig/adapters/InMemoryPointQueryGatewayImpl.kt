package com.android.sig.adapters

import com.android.sig.businesslogic.entities.Point
import com.android.sig.businesslogic.gateways.PointQueryGateway

class InMemoryPointQueryGatewayImpl: PointQueryGateway {

    private var _points: List<Point> = ArrayList()

    override fun getPoints(): List<Point> {
        return this._points
    }

    fun setPoints(points: List<Point>) {
        this._points = points
    }
}