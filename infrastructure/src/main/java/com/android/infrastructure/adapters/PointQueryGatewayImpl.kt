package com.android.infrastructure.adapters

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.gateways.PointQueryGateway
import java.util.*

internal class PointQueryGatewayImpl: PointQueryGateway {
    override fun getPoints(): List<Point> {

        val bollene = Point(1, 44.2833, 4.75, "Bollène", TypeEnum.RUIN, "Comment about Bollène")
        val orange = Point(2, 44.1333, 4.8, "Orange", TypeEnum.ARCHEOLOGIC_SITE, "Comment about Orange")

        return Arrays.asList(bollene, orange) // TODO
    }
}