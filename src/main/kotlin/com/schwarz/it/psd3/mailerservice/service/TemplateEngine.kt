package com.schwarz.it.psd3.mailerservice.service


import com.schwarz.it.psd3.mailerservice.model.EmailTemplate
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.springframework.stereotype.Service
import java.io.StringWriter


@Service
class TemplateEngine(private val velocityEngine: VelocityEngine)
{
    fun generateContent(template: String, data: Map<String, Any>): String {



        val context = VelocityContext()
        data.forEach { key, value -> context.put(key, value) }

        val writer = StringWriter()
        velocityEngine.evaluate(context, writer, "", template)

        return writer.toString()
    }
}