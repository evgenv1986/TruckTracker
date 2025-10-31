package domain.truck

class GeoCoordinate {
    private val longitude: Int
    private val latitude: Int

    constructor(latitude: Int, longitude: Int){
        this.latitude = latitude
        this.longitude = longitude
    }
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
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GeoCoordinate) return false
        return this.latitude == other.latitude &&
                this.longitude == other.longitude
    }

    override fun hashCode(): Int {
        var result = latitude.hashCode()
        return result
    }
}

sealed class CreateGeoCoordinateError (message: String) : Exception(message)  {
    object LatitudeOutOfBound: CreateGeoCoordinateError("широта выходит за допустимые границы")
    object LongitudeOutOfBound: CreateGeoCoordinateError("долгота выходит за допустимые границы")
}