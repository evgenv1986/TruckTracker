package org.example.truck.main
import domain.truck.GeoCoordinate
import domain.truck.VIN
import java.time.OffsetDateTime

enum class TruckState{
    MOVING(),
    PARKED(),
    NO_SIGNAL()
}
class Truck {
    private lateinit var updateTime: OffsetDateTime
    private lateinit var coordinate: GeoCoordinate
    private lateinit var vin: VIN
    private val state: TruckState

    constructor(vin: VIN, coordinate: GeoCoordinate, truckState: TruckState, updateTime: OffsetDateTime) {
        this.vin = vin
        this.coordinate = coordinate
        this.state = truckState
        this.updateTime = updateTime

    }

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
    fun state(): TruckState {
        if (lastUpdateLessMinute()) {
            changeState(TruckState.MOVING)
        }
        if (lastUpdateLessThreeMinute()) {
            changeState(TruckState.PARKED)
        }
        if (lastUpdateMoreThreeMinute()) {
            changeState(TruckState.NO_SIGNAL)
        }
        return this.state
    }

    private fun changeState(state: TruckState) {
        TODO("Not yet implemented")
    }

    private fun lastUpdateMoreThreeMinute(): Boolean {
        TODO("Not yet implemented")
    }

    private fun lastUpdateLessThreeMinute(): Boolean {
        TODO("Not yet implemented")
    }

    fun lastUpdateLessMinute(): Boolean {
        return updateTime() <= OffsetDateTime.now().plusMinutes(1)
    }

    fun updateTime(): OffsetDateTime{
        return this.updateTime
    }

}
