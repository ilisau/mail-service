package com.solvd.mailservice.web;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import com.solvd.mailservice.service.property.MailProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.File;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final MailProperties mailProperties;

    /**
     * Creates java mail sender.
     *
     * @return java mail sender
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol",
                mailProperties.getProperties()
                        .getProperty("mail.transport.protocol"));
        props.put("mail.smtp.auth",
                mailProperties.getProperties()
                        .getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable",
                mailProperties.getProperties()
                        .getProperty("mail.smtp.starttls.enable"));
        props.put("mail.debug",
                mailProperties.getProperties()
                        .getProperty("mail.debug"));
        return mailSender;
    }

    /**
     * Creates consumer xml.
     *
     * @return consumer xml
     */
    @SneakyThrows
    @Bean
    public XML consumerXml() {
        return new XMLDocument(
                new File("src/main/resources/kafka/consumer.xml")
        );
    }

}
