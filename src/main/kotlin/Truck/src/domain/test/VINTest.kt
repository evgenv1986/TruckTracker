package org.example.truck.src.domain.test

import org.junit.Assert
import org.junit.Test
import org.testng.Assert.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class VINTest {
    @Test
    fun `low len bound of vin`() {
        val lowLen = 11
        val vin = VIN.from("12345678901")
        Assert.assertEquals(11, lowLen)
        Assert.assertTrue(lowLen <= vin.length())
    }
    @Test
    fun `vin Len More Than Low-Len`() {
        val lowLen = 11
        val vin = VIN.from("12345678901")
        Assert.assertEquals(11, lowLen)
        Assert.assertTrue(lowLen <= vin.length())
    }
    @Test
    fun `successfully create vin with check Low Length`() {
        val lowLen = 11
        val vin = VIN.from("12345678901")
        Assert.assertEquals(11, lowLen)
        Assert.assertTrue(lowLen <= vin.length())
    }
    @Test
    fun `should throw ValueLessWhenLowLen when VIN too short`() {
        val exception = assertFailsWith<CreateVINError.ValueLessWhenLowLen> {
            VIN.from("1234567890")
        }
        assertEquals(
            "длина символов вин меньше допустимого минимума",
            exception.message
        )
    }
}

data class VIN(private val value: String){
    companion object {
        private const val LOWLEN = 11
        fun from(value: String): VIN {
            if (!valueLenMoreThanLowLen(value)){
                throw CreateVINError.ValueLessWhenLowLen
            } else return VIN(value)
        }
        private fun valueLenMoreThanLowLen(value: String): Boolean{
            return LOWLEN <= value.length
        }
    }
    public fun length(): Int {
        return value.length
    }

}

sealed class CreateVINError(message: String) : Exception(message) {
    object ValueLessWhenLowLen: CreateVINError("длина символов вин меньше допустимого минимума")
}
