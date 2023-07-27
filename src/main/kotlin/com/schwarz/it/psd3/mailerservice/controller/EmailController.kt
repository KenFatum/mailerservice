package com.schwarz.it.psd3.mailerservice.controller

import com.schwarz.it.psd3.mailerservice.model.EmailRequest
import com.schwarz.it.psd3.mailerservice.service.EmailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailController(private val emailService: EmailService) {
    @PostMapping
    fun sendMail(@RequestBody request: EmailRequest): ResponseEntity<String> {
        return try {
            emailService.sendEmail(request)
            ResponseEntity.ok("Email sent successfully.")
        }
        catch(e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: ${e.message}")
        }
    }
}