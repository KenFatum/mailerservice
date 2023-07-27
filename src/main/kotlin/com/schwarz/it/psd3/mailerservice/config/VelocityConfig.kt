package com.schwarz.it.psd3.mailerservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Properties
import org.apache.velocity.app.VelocityEngine

@Configuration
class VelocityConfig {
    @Bean
    fun velocityEngine(): VelocityEngine {
        val props = Properties()
            props.setProperty("resource.loader", "class")
            props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader")
            return VelocityEngine(props)
    }
}