package domain.truck.test
import domain.truck.CreateVINError
import domain.truck.GeoCoordinate
import domain.truck.VIN
import org.example.truck.main.Truck
import org.example.truck.main.TruckState
import org.junit.Test
import java.time.OffsetDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TruckTest {
    @Test
    fun `create truck with vin, coordinate, state, update time`() {
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        val parked = TruckState.PARKED
        val updateTime = OffsetDateTime.now()
        val Truck = Truck(vin, coordinate, parked, updateTime)

    }
    @Test
    fun `truck should be created with not correct vin` () {
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = 10
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        val parked = TruckState.PARKED
        val updateTime = OffsetDateTime.now()

        assertEquals("vin не должен содежать маленькие буквы",
            assertFailsWith<CreateVINError.UpperCase> {
                Truck.from(vin= "!овыаловыла", coordinate, parked, updateTime,)}.message)
        }
    @Test
    fun `truck should be created with not correct coordinate` () {
        val vin = VIN.from ("YVCBVCB44234343")
        val latitude = -1000
        val longitude = 20
        val coordinate = GeoCoordinate.from(latitude, longitude)
        val parked = TruckState.PARKED
        val updateTime = OffsetDateTime.now()

        assertEquals("error message",
            assertFailsWith<CreateVINError.UpperCase> {
                Truck.from(
                    vin= "YVCBVCB44234343",
                    latitude = -1000,
                    longitude = 20,
                    parked,
                    updateTime)}
                .message)
    }
}