package org.example.Truck.src.domain.test

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

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
    @Test
    fun `canT create with latitude out of bound`(){
        assertEquals("широта выходит за допустимые границы",
            assertFailsWith<CreateGeoCoordinateError.LatitudeOutOfBound> {
                GeoCoordinate.from (-200, 10)}.message)
    }

    @Test
    fun `canT create with longitude out of bound`(){
        assertEquals("долгота выходит за допустимые границы",
            assertFailsWith<CreateGeoCoordinateError.LongitudeOutOfBound> {
                GeoCoordinate.from (20, 250)}.message)
    }
}

class GeoCoordinate(latitude1: Int, longitude1: Int) {
    companion object {
        private const val LOWER_LATITUDE = -90
        private const val UPPER_LATITUDE = 90

        private const val LOWER_LONGITUDE = -180
        private const val UPPER_LONGITUDE = 180
        fun from(latitude: Int, longitude: Int): GeoCoordinate {
            if (latitude < LOWER_LATITUDE || latitude > UPPER_LATITUDE){
                throw CreateGeoCoordinateError.LatitudeOutOfBound
            }
            if (longitude < LOWER_LONGITUDE || longitude > UPPER_LONGITUDE){
                throw CreateGeoCoordinateError.LongitudeOutOfBound
            }
            return GeoCoordinate(latitude, longitude)
        }
    }
}

sealed class CreateGeoCoordinateError (message: String) : Exception(message)  {
    object LatitudeOutOfBound: CreateGeoCoordinateError("широта выходит за допустимые границы")
    object LongitudeOutOfBound: CreateGeoCoordinateError("долгота выходит за допустимые границы")
}
