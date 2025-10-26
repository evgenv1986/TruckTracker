package Truck.src.domain.main.Truck.src.domain.main

class GeoCoordinate(latitude1: Int, longitude1: Int) {
    companion object {
        private const val LOWER_LATITUDE = -90
        private const val UPPER_LATITUDE = 90

        private const val LOWER_LONGITUDE = -180
        private const val UPPER_LONGITUDE = 180
        fun from(latitude: Int, longitude: Int): GeoCoordinate {
            when {
                latitude !in LOWER_LATITUDE..UPPER_LATITUDE ->
                    throw CreateGeoCoordinateError.LatitudeOutOfBound
                longitude !in LOWER_LONGITUDE..UPPER_LONGITUDE ->
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