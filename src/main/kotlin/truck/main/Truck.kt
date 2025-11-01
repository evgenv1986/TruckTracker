package org.example.truck.main
import domain.truck.GeoCoordinate
import domain.truck.VIN
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.abs

enum class TruckState{
    MOVING(),
    PARKED(),
    NO_SIGNAL()
}
class Truck {
    private var updateTime: OffsetDateTime
    private var state: TruckState
    private var coordinate: GeoCoordinate
    private val vin: VIN

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
        if (lessThan(1)) {
            changeState(TruckState.MOVING)
        }
        if (moreThan(1) and lessOrEqualThan(3)) {
            changeState(TruckState.PARKED)
        }
        if (moreThan(3)) {
            changeState(TruckState.NO_SIGNAL)
        }
        return this.state
    }
    private fun changeState(state: TruckState) {
        this.state = state
    }
    fun lastUpdateInMinutes(): Long{
        return abs(ChronoUnit.MINUTES.between(
            OffsetDateTime.now(),
            updateTime))
    }
    fun lessThan(minutes: Long): Boolean{
        return lastUpdateInMinutes() < minutes
    }
    fun moreThan(minutes: Long): Boolean{
        return lastUpdateInMinutes() > minutes
    }
    fun lessOrEqualThan(minutes: Long): Boolean{
        return lastUpdateInMinutes() <= minutes
    }
    fun updateTime(): OffsetDateTime{
        return this.updateTime
    }

    fun moveTo(to: GeoCoordinate, movingTime: OffsetDateTime) {
        if (coordinate.equals(to)) return
        if (movingTime < updateTime()){
            throw MoveTruckError.MovingTimeLessThanPreviousTime
        }
        coordinate = to
        updateTime = movingTime
        if (lessThan(1)) {
            changeState(TruckState.MOVING)
        }
        if (moreThan(1) and lessOrEqualThan(3)) {
            changeState(TruckState.PARKED)
        }
        if (moreThan(3)) {
            changeState(TruckState.NO_SIGNAL)
        }
//        addEvent(TrackMovedDomainEvent);
    }

}

sealed class MoveTruckError(message: String): Exception(message) {
    object MovingTimeLessThanPreviousTime: MoveTruckError(
        "Время обновления позиции меньше последнего времени обновления")
}