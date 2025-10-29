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

}
