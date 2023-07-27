package com.schwarz.it.psd3.mailerservice.service

import com.schwarz.it.psd3.mailerservice.model.EmailTemplate
import com.schwarz.it.psd3.mailerservice.model.RecipientConfig
import com.schwarz.it.psd3.mailerservice.model.SenderConfig
import com.schwarz.it.psd3.mailerservice.model.SmtpConfig
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


class EmailTemplateService(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val bodyTemplate: String,
    val subjectTemplate: String,
    val senderConfig: SenderConfig,
    val serverConfig: SmtpConfig,
    val recipientConfig: RecipientConfig,
    val authorities: List<String>
) {
    interface EmailTemplateService {
        fun createTemplate(template: EmailTemplate): EmailTemplate
        fun getTemplate(id: String): EmailTemplate?
        fun updateTemplate(id: String, template: EmailTemplate): EmailTemplate
        fun deleteTemplate(id: String): Boolean
    }
}