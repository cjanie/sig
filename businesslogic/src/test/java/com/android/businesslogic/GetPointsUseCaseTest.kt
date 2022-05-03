package com.android.businesslogic

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.usecases.GetPointsUseCase
import com.android.businesslogic.inmemoryadapters.InMemoryPointQueryGatewayImpl
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*


class GetPointsUseCaseTest {

    @Test
    fun retrieves2PointsWhenThereAre2PointsAvailable() {
        val pointQueryGateway = InMemoryPointQueryGatewayImpl()
        val pointA = Point(1, 1.2, 1.3, "Point A", TypeEnum.CASTEL, "Comment about Point A")
        val pointB = Point(2, 1.5, 1.7, "Point B", TypeEnum.RUIN, "Comment about Point B")
        pointQueryGateway.setPoints(Arrays.asList(pointA, pointB))
        assertEquals(2, GetPointsUseCase(pointQueryGateway).handle().size)
    }

    @Test
    fun retrievesNothingWhenNoPointsAreAvailable() {
        val pointQueryGateway = InMemoryPointQueryGatewayImpl()
        assertEquals(0, GetPointsUseCase(pointQueryGateway).handle().size)
    }
}