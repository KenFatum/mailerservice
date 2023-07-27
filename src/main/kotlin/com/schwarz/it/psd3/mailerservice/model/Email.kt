package com.schwarz.it.psd3.mailerservice.model

data class Email(
    val from: String,
    val recipients: List<String>,
    val subject: String,
    val body: String
) {}