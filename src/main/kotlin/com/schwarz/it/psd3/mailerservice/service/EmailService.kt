package com.schwarz.it.psd3.mailerservice.service

import com.schwarz.it.psd3.mailerservice.model.EmailRequest
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val templateEngine: TemplateEngine,
    private val javaMailSender: JavaMailSender
) {
    fun sendEmail(request: EmailRequest) {
        val template = getTemplateById(request.templateId) // Methode, um die Vorlage basierend auf der ID zu holen.

        val context = Context().apply {
            setVariables(request.supplementalData)
        }

        val emailContent = templateEngine.process(template.bodyTemplate, context)

        val emailSubject = templateEngine.process(template.subjectTemplate, context)

        val message = javaMailSender.createMimeMessage()
        MimeMessageHelper(message).apply {
            setFrom(template.sendConfig)
            setTo(request.recipientEmails ?: template.recipientConfig)
            setSubject(emailSubject)
            setText(emailContent)
        }
        javaMailSender.send(message)
        log.info("EMail sent successfully.")
    }



}