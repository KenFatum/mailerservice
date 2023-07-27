package com.schwarz.it.psd3.mailerservice.model

data class SmtpConfig(
    val hostname: String,
    val port: Int,
    val protocol: String,
    val username: String,
    val password: String
) {}