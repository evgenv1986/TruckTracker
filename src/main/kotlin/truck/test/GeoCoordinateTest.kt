package domain.truck.test

import domain.truck.CreateGeoCoordinateError
import domain.truck.GeoCoordinate
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

