package common.src.main.types.base

import java.time.OffsetDateTime
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.incrementAndFetch

open class DomainEvent(
    val id: EventId = EventId.new(),
    val created: OffsetDateTime = OffsetDateTime.now()) {

}

data class EventId(val id: Long ) {
    companion object {
        @OptIn(ExperimentalAtomicApi::class)
        fun new(): EventId {
            return EventId (AtomicLong(0).incrementAndFetch())
        }
    }
}
