package Truck.src.domain.main

import org.example.truck.src.domain.test.CreateVINError

data class VIN(private val value: String){
    companion object {
        private const val LOWLEN = 11
        private const val HIGHLEN = 17
        fun from(value: String): VIN {
            if (!valueLenMoreThanLowLen(value)){
                throw CreateVINError.ValueLessWhenLowLen
            }
            if (valueLenMoreThanHighLen(value)){
                throw CreateVINError.ValueMoreWhenHighLen
            }
            return VIN(value)
        }

        private fun valueLenMoreThanHighLen(value: String): Boolean {
            return HIGHLEN <= value.length
        }

        private fun valueLenMoreThanLowLen(value: String): Boolean{
            return LOWLEN <= value.length
        }
    }
    public fun length(): Int {
        return value.length
    }

}