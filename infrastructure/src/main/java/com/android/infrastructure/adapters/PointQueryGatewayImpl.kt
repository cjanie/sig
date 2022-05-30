package com.android.infrastructure.adapters

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.gateways.PointQueryGateway
import com.android.infrastructure.dao.PointDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlin.collections.ArrayList

internal class PointQueryGatewayImpl(private val pointDao: PointDao): PointQueryGateway {

    override fun getPoints(): Flow<List<Point>> {
        return this.pointDao.getPoints()
            .map {
                    pointsDTO -> this.format(pointsDTO)
            }
            .flowOn(Dispatchers.IO)
    }

    // FORMAT TRANSFORMATION
    private fun format(pointsDTO: List<com.android.infrastructure.entities.Point>): List<Point> {
        val points: MutableList<Point> = ArrayList()
        if(!pointsDTO.isNullOrEmpty()) {
            for(pointDto in pointsDTO) {
                val point = Point(
                    pointDto.id,
                    pointDto.latitude,
                    pointDto.longitude,
                    pointDto.pointName,
                    this.getTypeEnum(pointDto.type!!),
                    pointDto.note
                )
                points.add(point)
            }
        }
        return points
    }

    private fun getTypeEnum(type: String) : TypeEnum {
        val typeEnum = when (type) {
            TypeEnum.RUIN.toString() -> TypeEnum.RUIN
            TypeEnum.CASTEL.toString() -> TypeEnum.CASTEL
            TypeEnum.WALL.toString() -> TypeEnum.WALL
            TypeEnum.ARCHEOLOGIC_SITE.toString() -> TypeEnum.ARCHEOLOGIC_SITE
            TypeEnum.HISTORIC_SITE.toString() -> TypeEnum.HISTORIC_SITE
            TypeEnum.OTHER_TYPE.toString() -> TypeEnum.OTHER_TYPE

            else -> {TypeEnum.OTHER_TYPE}
        }
        return typeEnum
    }


}