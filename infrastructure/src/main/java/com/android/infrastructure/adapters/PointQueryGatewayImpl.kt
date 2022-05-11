package com.android.infrastructure.adapters

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.gateways.PointQueryGateway
import com.android.infrastructure.dao.PointDao
import java.util.*
import kotlin.collections.ArrayList

internal class PointQueryGatewayImpl(private val pointDao: PointDao): PointQueryGateway {
    override fun getPoints(): List<Point> {

        val bollene = Point(1, 44.2833, 4.75, "Bollène", TypeEnum.RUIN, "Comment about Bollène")
        val orange = Point(2, 44.1333, 4.8, "Orange", TypeEnum.ARCHEOLOGIC_SITE, "Comment about Orange")

        return Arrays.asList(bollene, orange) // TODO


/*
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
        System.out.println(points.size)
        return points

*/
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