package com.schwarz.it.psd3.mailerservice.model

class SupplementalData(private val data: Map<String, Any>) {
    fun getValue(key: String): Any? {
        return data[key]
    }
}