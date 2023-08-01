package com.schwarz.it.psd3.mailerservice.model

data class EmailRequest (val templateId: String,
                         val recipientEmails: List<String>? = null,
                         val supplementalData: Map<String, Any>)

{}