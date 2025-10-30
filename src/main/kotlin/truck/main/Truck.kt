package org.example.truck.main
import domain.truck.GeoCoordinate
import domain.truck.VIN
import java.time.OffsetDateTime

enum class TruckState{
    MOVING(),
    PARKED(),
    NO_SIGNAL()
}
class Truck(vin: VIN, coordinate: GeoCoordinate, truckState: TruckState, updateTime: OffsetDateTime) {
    companion object {
        fun from(
            vin: VIN,
            coordinate: GeoCoordinate)
        : Truck {
            val truckState: TruckState  = TruckState.MOVING
            val updateTime: OffsetDateTime = OffsetDateTime.now()
            return Truck(vin, coordinate, truckState, updateTime)
        }
    }

}
