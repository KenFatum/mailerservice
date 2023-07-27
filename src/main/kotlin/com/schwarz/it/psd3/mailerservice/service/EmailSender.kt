package com.schwarz.it.psd3.mailerservice.service

import jakarta.mail.Message
import jakarta.mail.MessagingException
import jakarta.mail.Session
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import jakarta.mail.Transport
import jakarta.mail.Authenticator
import jakarta.mail.PasswordAuthentication
import java.sql.DriverManager.println


class EmailSender(
    private val smtpHost: String,
    private val smtpPort: Int,
    private val username: String,
    private val password: String
) {
    fun sendEmail(to: String, subject: String, body: String) {
        val properties = System.getProperties()
        properties.setProperty("mail.smtp.host", smtpHost)
        properties.setProperty("mail.smtp.port", smtpPort.toString())
        properties.setProperty("mail.smtp.auth", "true")
        properties.setProperty("mail.smtp.starttls.enable", "true")


        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication()= PasswordAuthentication(username, password)
        })
        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(username))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(to))
            message.subject = subject
            message.setText(body)

            Transport.send(message)
            println("Sent message successfully....")
        }
        catch(messagingException: MessagingException) {
            messagingException.printStackTrace()
        }
    }
}