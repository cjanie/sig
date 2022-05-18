package com.android.infrastructure.adapters

import android.util.Log
import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.gateways.PointQueryGateway
import com.android.infrastructure.dao.PointDao
import java.util.*
import kotlin.collections.ArrayList

internal class PointQueryGatewayImpl(private val pointDao: PointDao): PointQueryGateway {

    private val TAG = "POINT QUERY GATEWAY"

    override fun getPoints(): List<Point> {

        val points: MutableList<Point> = ArrayList();
        val pointsDTO = this.pointDao.getPoints();
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