package com.solvd.mailservice.service.impl;

import com.solvd.mailservice.service.Mail;
import com.solvd.mailservice.service.property.MailLinkProperties;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResetMail implements Mail {

    private final Configuration configuration;
    private final JavaMailSender mailSender;
    private final MailLinkProperties mailLinkProperties;

    @Override
    @SneakyThrows
    public Mono<Void> send(Map<String, Object> params) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Restore password");
        helper.setTo(params.get("user.email").toString());
        String emailContent = getRestoreEmailContent(params);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
        return Mono.empty();
    }

    @SneakyThrows
    private String getRestoreEmailContent(Map<String, Object> params) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", params.get("user.name") + " " + params.get("user.surname"));
        model.put("link", mailLinkProperties.getRestore() + params.get("token"));
        configuration.getTemplate("restore.ftlh")
                .process(model, stringWriter);
        return stringWriter.getBuffer()
                .toString();
    }

}
