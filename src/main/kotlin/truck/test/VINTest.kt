<<<<<<<< HEAD:src/main/kotlin/truck/test/VINTest.kt
package org.example.truck.test
========
package domain.truck.test
>>>>>>>> origin/master:src/main/kotlin/domain/truck/test/VINTest.kt

import domain.truck.CreateVINError
import domain.truck.VIN
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class VINTest {
    @Test
    fun `low len bound of vin`() {
        val lowLen = 11
        val vin = VIN.Companion.from("12345678901")
        Assert.assertEquals(11, lowLen)
        Assert.assertTrue(lowLen <= vin.length())
    }
    @Test
    fun `vin Len More Than Low-Len`() {
        val lowLen = 11
        val vin = VIN.Companion.from("12345678901")
        Assert.assertEquals(11, lowLen)
        Assert.assertTrue(lowLen <= vin.length())
    }
    @Test
    fun `successfully create vin with check Low Length`() {
        val lowLen = 11
        val vin = VIN.Companion.from("12345678901")
        Assert.assertEquals(11, lowLen)
        Assert.assertTrue(lowLen <= vin.length())
    }
    @Test
    fun `should throw ValueLessWhenLowLen when VIN too short`() {
        val exception = assertFailsWith<CreateVINError.ValueLessWhenLowLen> {
            VIN.Companion.from("1234567890")
        }
        assertEquals(
            "длина символов вин меньше допустимого минимума",
            exception.message
        )
    }
    @Test
    fun `should throw ValueLessWhenHighLen when VIN too long` () {
        val exception = assertFailsWith<CreateVINError.ValueMoreWhenHighLen> {
            VIN.Companion.from("123456789012345678")
        }
        assertEquals(
            "длина символов вин больще допустимого максимума",
            exception.message
        )
    }
    @Test
    fun `should throw alpha numeric error on create vin`() {
        val exception = assertFailsWith<CreateVINError.AplhaNumericError> {
            VIN.Companion.from("123Л4567890")
        }
        assertEquals("Разрешены только латинские буквы и цифры", exception.message)
    }
    @Test
    fun `successfully create vin with alpha numeric chars`() {
        VIN.Companion.from("123E4567890")
    }
    @Test
    fun `should throw with forbiden chars I O Q`() {
        assertEquals(
            "Символы I, O, Q запрещены",
            assertFailsWith<CreateVINError.ForbidenCharsError> {
                VIN.Companion.from("123I4567890")
            }.message
        )
    }
    @Test
    fun `should throw with space`() {
        assertEquals(
            "vin не должен содержать пробелы",
            assertFailsWith<CreateVINError.SpaceError> {
                VIN.Companion.from("123 45678901")
            }.message
        )
    }
    @Test
    fun `should throw with special chars`() {
        assertEquals(
            "vin не должен содержать спецсимволы",
            assertFailsWith<CreateVINError.SpecialCharsError> {
                VIN.Companion.from("1234&5678901")
            }.message
        )
    }
    @Test
    fun `should throw with empty value`() {
        assertEquals(
            "vin не должен пустым",
            assertFailsWith<CreateVINError.EmptyValue> {
                VIN.Companion.from("")
            }.message
        )
    }
    @Test
    fun `should throw with not upper chars`() {
        assertEquals(
            "vin не должен содежать маленькие буквы",
            assertFailsWith<CreateVINError.UpperCase> {
                VIN.Companion.from("VVNBVBnVBNVNBV")
            }.message
        )
    }
}