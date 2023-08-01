package com.schwarz.it.psd3.mailerservice.service

import com.schwarz.it.psd3.mailerservice.model.EmailRequest
import jakarta.mail.internet.MimeMessage
import org.hibernate.query.sqm.tree.SqmNode.log
import org.thymeleaf.context.Context
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val templateEngine: TemplateEngine,
    private val javaMailSender: JavaMailSender,
    private val templateService: EmailTemplateService
) {
    fun sendEmail(request: EmailRequest) {
        val template = templateService.getTemplateById(request.templateId) // Methode, um die Vorlage basierend auf der ID zu holen.
        if (template == null) {
            return
        }

        val emailContent = templateEngine.generateContent(template.body, request.supplementalData)

        val emailSubject = templateEngine.generateContent(template.subject, request.supplementalData)

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