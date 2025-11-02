package truck.`in-memory-persistence`

import truck.main.TruckId
import truck.main.TruckIdFactory
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.incrementAndFetch


class TruckLongIdFactory: TruckIdFactory{
    @OptIn(ExperimentalAtomicApi::class)
    override fun new(): TruckId {
        return TruckId (AtomicLong(0).incrementAndFetch())
    }

}