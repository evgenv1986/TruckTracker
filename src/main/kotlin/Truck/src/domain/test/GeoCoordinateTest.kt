package org.example.Truck.src.domain.test

import org.junit.Test
import kotlin.test.assertEquals

class GeoCoordinateTest {
    @Test
    fun `successfully create geo coordinate`() {
        val lowerLatitude = -90
        val upperLatitude = 90

        val lowerLongitude = -180
        val upperLongitude = 180

        val latitude = 10
        val longitude = 20

        val geoCoordinate = GeoCoordinate.from(latitude, longitude)
    }
}

class GeoCoordinate(latitude1: Int, longitude1: Int) {
    companion object {
        fun from(latitude: Int, longitude: Int): GeoCoordinate {
            return GeoCoordinate(latitude, longitude)
        }
    }

}
