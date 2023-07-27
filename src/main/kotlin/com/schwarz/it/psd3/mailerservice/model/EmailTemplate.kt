package com.schwarz.it.psd3.mailerservice.model

data class EmailTemplate(
    val id: String,
    val body: String,
    val subject: String,
    val from: String,
    val replyTo: String?,
    val senderConfig: SenderConfig,
    val smtpConfig: SmtpConfig,
    val recipientConfig: RecipientConfig? = null,
    val authorities: List<String>
) {}