package common.src.main.types.base

open class DomainEntity<T> protected constructor(
    val id: T,
    var version: Version,
){
    private var events = ArrayList<DomainEvent>()
    protected fun addEvent(event: DomainEvent){
        if (events.isEmpty()){
            version = version.next()
        }
        events.add(event)
    }
    public fun popEvents(): List<DomainEvent>{
        val res = events
        this.events = ArrayList()
        return res
    }
}

data class Version (private val value: Long) : ValueObject<Long>{
    fun next() = Version(value + 1)
    companion object {
        fun new() = Version(0L)
    }
}

