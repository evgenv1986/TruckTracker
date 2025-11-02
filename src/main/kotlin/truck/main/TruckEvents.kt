package truck.main

import common.src.main.types.base.DomainEvent

data class TrackMovedDomainEvent (private val truckId: TruckId) : DomainEvent() {
}