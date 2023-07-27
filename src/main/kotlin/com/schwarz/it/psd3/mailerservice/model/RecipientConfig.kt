package com.schwarz.it.psd3.mailerservice.model

class RecipientConfig(
    val to: List<String>,
    val cc: List<String>? = null,
    val bcc: List<String>? = null
) {}