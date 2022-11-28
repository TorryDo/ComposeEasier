package com.torrydo.compose_easier.navigation

data class NavData(
    var data: Any? = null
) {
    fun <T> get(): T {

        return try {
            data as T
        } catch (e: Exception) {
            throw TypeCastException("destination's expected data does not matched with the passed data")
        }

    }

}