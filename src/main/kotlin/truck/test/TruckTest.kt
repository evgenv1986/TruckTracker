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
        val parked = TruckState.PARKED
        val updateTime = OffsetDateTime.now()
        Truck.from(vin, coordinate)
    }

    fun fixtureTruck (): Truck {
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        val parked = TruckState.PARKED
        val updateTime = OffsetDateTime.now()
        return Truck.from(vin, coordinate)
    }

    @Test
    fun `state truck after create is moving and update time less one minute` (){
        val truck = fixtureTruck()
        assertEquals(TruckState.MOVING, truck.state())
        assertTrue(OffsetDateTime.now().plusMinutes(1) >= truck.updateTime())
    }
    @Test
    fun `state is MOVING and updateTime more when one minute`(){
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        val state = TruckState.MOVING
        val pastMinute = OffsetDateTime.now()
        val truck = Truck(vin, coordinate, state, pastMinute)
        assertTrue(TruckState.MOVING == truck.state())
    }
    @Test
    fun `change state to PARKED when last update between 1 and 3 minute` (){
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        val state = TruckState.MOVING
        val pastMinute = OffsetDateTime.now().minusMinutes(2)
        val truck = Truck(vin, coordinate, state, pastMinute)
        assertTrue(TruckState.PARKED == truck.state())
    }
    @Test
    fun `change state to NO_SIGNAL when last update between 1 and 3 minute` (){
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        val state = TruckState.MOVING
        val pastMinute = OffsetDateTime.now().minusMinutes(4)
        val truck = Truck(vin, coordinate, state, pastMinute)
        assertTrue(TruckState.NO_SIGNAL == truck.state())
    }
    @Test
    fun `lastUpdateLessMinute` () {
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        val state = TruckState.MOVING
        val updateTime = OffsetDateTime.now()
        val truck = Truck(vin, coordinate, state, updateTime)
        assertTrue(truck.lastUpdateLessMinute())
    }

}

