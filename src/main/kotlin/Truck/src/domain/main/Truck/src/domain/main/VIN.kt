package Truck.src.domain.main

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

sealed class CreateVINError(message: String) : Exception(message) {
    object ValueLessWhenLowLen: CreateVINError("длина символов вин меньше допустимого минимума")
    object ValueMoreWhenHighLen: CreateVINError("длина символов вин больще допустимого максимума")
}
