package truck.main

import common.src.main.types.base.AggregateRoot
import common.src.main.types.base.Version
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
open class Truck : AggregateRoot<TruckId> {
    private lateinit var updateTime: OffsetDateTime
    private lateinit var state: TruckState
    private lateinit var coordinate: GeoCoordinate
    private val vin: VIN

    constructor(
        id: TruckId,
        version: Version,
        vin: VIN,
        coordinate: GeoCoordinate,
        truckState: TruckState,
        updateTime: OffsetDateTime
    )
    : super(id, version) {
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
            return Truck(
                id = TruckId(-1),
                version = Version.new(),
                vin,
                coordinate,
                truckState,
                updateTime)
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
        addEvent(TrackMovedDomainEvent(this.id));
    }

}

sealed class MoveTruckError(message: String): Exception(message) {
    object MovingTimeLessThanPreviousTime: MoveTruckError(
        "Время обновления позиции меньше последнего времени обновления")
}