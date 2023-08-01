package com.schwarz.it.psd3.mailerservice.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.schwarz.it.psd3.mailerservice.model.EmailTemplate
import com.schwarz.it.psd3.mailerservice.model.RecipientConfig
import com.schwarz.it.psd3.mailerservice.model.SenderConfig
import com.schwarz.it.psd3.mailerservice.model.SmtpConfig
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.stereotype.Service
import java.io.File

@Service
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
    private val templates: MutableMap<String, EmailTemplate> = mutableMapOf()

    init {
        // Laden der E-Mail-Templates bei der Initialisierung (falls gewünscht)
        loadTemplatesFromResources()
    }

    fun getTemplateById(id: String): EmailTemplate? {
        return templates[id]
    }

    private fun loadTemplatesFromResources() {
        // Pfad zu Ihrem "resources" Verzeichnis, wo die JSON-Dateien für E-Mail-Templates gespeichert sind
        val resourcesPath = "resources/templates.login_successfully.login_successfully.login"

        val objectMapper = jacksonObjectMapper()

        File(resourcesPath).listFiles { file -> file.extension == "json" }?.forEach { file ->
            val template: EmailTemplate = objectMapper.readValue(file)
            templates[template.id] = template
        }
    }
}