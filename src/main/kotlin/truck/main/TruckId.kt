package truck.main

import common.src.main.types.base.ValueObject

data class TruckId (private val id: Long): ValueObject<Long> {
    fun toLong() = id
}

interface TruckIdFactory{
    fun new(): TruckId
}
