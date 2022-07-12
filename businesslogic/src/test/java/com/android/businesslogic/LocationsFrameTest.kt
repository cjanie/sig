package com.android.businesslogic

import com.android.businesslogic.domain.entities.Geolocation
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class LocationsFrame(private val latitudes: List<Double>, private val longitudes: List<Double>) {

    init {
        Collections.sort(this.latitudes)
        Collections.sort(this.longitudes)
    }

    private val smallestLongitude = this.longitudes[0]
    private val biggestLongitude = this.longitudes[this.longitudes.size - 1]
    private val biggestLatitude = this.latitudes[this.latitudes.size - 1]
    private val smallestLatitude = this.latitudes[0]

    val southWestPoint = Geolocation(this.smallestLatitude, this.smallestLongitude)

    val northEastPoint = Geolocation(this.biggestLatitude, this.biggestLongitude)

}

class LocationsFrameTest {

    @Test
    fun definesSouthWestPointAtSouthAndWestLimits() {
        val latitudes: List<Double> = listOf(-0.8, -0.7, 0.8, 0.0)
        val longitudes: List<Double> = listOf(-1.6, -1.7, -1.8, 0.0)
        val frame = LocationsFrame(latitudes, longitudes)
        assertEquals(-0.8, frame.southWestPoint.latitude, 0.0)
        assertEquals(-1.8, frame.southWestPoint.longitude, 0.0)
    }

    @Test
    fun definesNorthEastPointAtNorthAndEastLimits() {
        val latitudes: List<Double> = listOf(-0.8, -0.7, 0.8, 0.0)
        val longitudes: List<Double> = listOf(-1.6, -1.7, -1.8, 0.0)
        val frame = LocationsFrame(latitudes, longitudes)
        assertEquals(0.8, frame.northEastPoint.latitude, 0.0)
        assertEquals(0.0, frame.northEastPoint.longitude, 0.0)
    }
}