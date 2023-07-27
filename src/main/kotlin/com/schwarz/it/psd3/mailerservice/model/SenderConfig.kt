package com.schwarz.it.psd3.mailerservice.model

data class SenderConfig(
   val from: String,
    val replyTo: String? = null
) {}