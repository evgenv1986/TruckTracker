package domain.truck.test
import domain.truck.GeoCoordinate
import domain.truck.VIN
import org.example.truck.main.Truck
import org.example.truck.main.TruckState
import org.junit.Test
import java.time.OffsetDateTime
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TruckTest {
    @Test
    fun `truck should be created with not correct vin` () {
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        Truck.from(vin, coordinate)
    }

    fun fixtureTruck (): Truck {
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        return Truck.from(vin, coordinate)
    }
    fun fixtureTruckWithMoveTime (moveTime: OffsetDateTime): Truck {
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        val state = TruckState.MOVING
        val truck = Truck(vin, coordinate, state, moveTime)
        return truck
    }
    @Test
    fun `state truck after create is moving and update time less one minute` (){
        val truck = fixtureTruck()
        assertEquals(TruckState.MOVING, truck.state())
        assertTrue(OffsetDateTime.now().plusMinutes(1) >= truck.updateTime())
    }
    @Test
    fun `state is MOVING and updateTime more when one minute`(){
        val pastMinute = OffsetDateTime.now()
        val truck = fixtureTruckWithMoveTime(pastMinute)
        assertTrue(TruckState.MOVING == truck.state())
    }
    @Test
    fun `change state to PARKED when last update between 1 and 3 minute` (){
        val pastMinute = OffsetDateTime.now().minusMinutes(2)
        val truck = fixtureTruckWithMoveTime(pastMinute)
        assertTrue(TruckState.PARKED == truck.state())
    }
    @Test
    fun `change state to NO_SIGNAL when last update between 1 and 3 minute` (){
        val pastMinute = OffsetDateTime.now().minusMinutes(4)
        val truck = fixtureTruckWithMoveTime(pastMinute)
        assertTrue(TruckState.NO_SIGNAL == truck.state())
    }
    @Test
    fun lastUpdateLessMinute() {
        val updateTime = OffsetDateTime.now()
        val truck = fixtureTruckWithMoveTime(updateTime)
        assertTrue(truck.lessThan(1))
        assertEquals(TruckState.MOVING, truck.state())
    }
    @Test
    fun lastUpdateLessThreeMinute() {
        val updateTime = OffsetDateTime.now().minusMinutes(2)
        val truck = fixtureTruckWithMoveTime(updateTime)
        assertTrue(truck.moreThan(1) and truck.lessOrEqualThan(3))
        assertEquals(TruckState.PARKED, truck.state())
    }
    @Test
    fun lastUpdateMoreThreeMinute() {
        val updateTime = OffsetDateTime.now().minusMinutes(4)
        val truck = fixtureTruckWithMoveTime(updateTime)
        assertTrue(truck.moreThan(3))
        assertEquals(TruckState.NO_SIGNAL, truck.state())
    }
    @Test
    fun `change state to PARKED with method moveTo`() {
        val truck = fixtureTruckWithMoveTime(
            OffsetDateTime.now().minusMinutes(3))
        assertEquals(TruckState.PARKED, truck.state())

    }
}



























