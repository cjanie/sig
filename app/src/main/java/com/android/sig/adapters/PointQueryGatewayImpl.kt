package com.android.sig.adapters

import com.android.sig.businesslogic.entities.Point
import com.android.sig.businesslogic.enums.TypeEnum
import com.android.sig.businesslogic.gateways.PointQueryGateway
import java.util.*

class PointQueryGatewayImpl: PointQueryGateway {
    override fun getPoints(): List<Point> {

        val bollene = Point(1, 44.2833, 4.75, "Bollène", TypeEnum.RUIN, "Comment about Bollène")
        val orange = Point(2, 1.5, 1.7, "Orange", TypeEnum.ARCHEOLOGIC_SITE, "Comment about Orange")

        return Arrays.asList(bollene, orange)
    }
}