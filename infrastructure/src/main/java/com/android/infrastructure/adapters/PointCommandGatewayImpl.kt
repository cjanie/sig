package com.android.infrastructure.adapters

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.gateways.PointCommandGateway
import com.android.infrastructure.dao.PointDao
import kotlinx.coroutines.flow.Flow

internal class PointCommandGatewayImpl(private val pointDao: PointDao): PointCommandGateway {

    override suspend fun save(point: Point): Long {
        val pointDto = com.android.infrastructure.entities.Point(
            null,
            point.latitude,
            point.longitude,
            point.name,
            point.type.toString(),
            point.note
        )
        val id: Long = this.pointDao.savePoint(pointDto)
        return id
    }
}