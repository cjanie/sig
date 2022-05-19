package com.android.businesslogic

import com.android.businesslogic.domain.entities.Point
import com.android.businesslogic.domain.enums.TypeEnum
import com.android.businesslogic.usecases.GetPointsUseCase
import com.android.businesslogic.inmemoryadapters.InMemoryPointQueryGatewayImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.*



class GetPointsUseCaseTest {

    @Test
    fun retrieves2PointsWhenThereAre2PointsAvailable() = runBlocking {
        val pointQueryGateway = InMemoryPointQueryGatewayImpl()
        val pointA = Point(1, 1.2, 1.3, "Point A", TypeEnum.CASTEL, "Comment about Point A")
        val pointB = Point(2, 1.5, 1.7, "Point B", TypeEnum.RUIN, "Comment about Point B")
        pointQueryGateway.setPoints(Arrays.asList(pointA, pointB))

        GetPointsUseCase(pointQueryGateway).handle().collect { points ->
            assertEquals(2, points.size)
        }
        //assertEquals(2, GetPointsUseCase(pointQueryGateway).handle().size)
    }

    @Test
    fun retrievesNothingWhenNoPointsAreAvailable() = runBlocking{
        val pointQueryGateway = InMemoryPointQueryGatewayImpl()
        GetPointsUseCase(pointQueryGateway).handle().collect { points ->
            assertEquals(0, points.size)
        }

    }
}