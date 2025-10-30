package org.example.truck.main
import domain.truck.GeoCoordinate
import domain.truck.VIN
import java.time.OffsetDateTime

enum class TruckState{
    MOVING(),
    PARKED(),
    NO_SIGNAK()
}
class Truck(vin: VIN, coordinate: GeoCoordinate, truckState: TruckState, updateTime: OffsetDateTime) {
    companion object {
        fun from(
            vin: VIN,
            coordinate: GeoCoordinate,
            truckState: TruckState,
            updateTime: OffsetDateTime)
        : Truck {
            return Truck(vin, coordinate, truckState, updateTime)
        }
    }

}
