package com.android.infrastructure.adapters

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.gateways.PointCommandGateway
import com.android.infrastructure.dao.PointDao

class PointCommandGatewayImpl(private val pointDao: PointDao): PointCommandGateway {

    override fun save(point: Point): Long {
        val pointDto:com.android.infrastructure.entities.Point
        = com.android.infrastructure.entities.Point(
            0,
            point.longitude,
            point.latitude,
            point.name,
            point.type.toString(),
            point.note
        )
        val id: Long = this.pointDao.savePoint(pointDto)
        System.out.println("database save: " + id + "%%%%%%%%%%%%%%%%%%%%%%")
        return id
    }
}