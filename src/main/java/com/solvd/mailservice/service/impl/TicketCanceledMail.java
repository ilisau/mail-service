package com.solvd.mailservice.service.impl;

import com.solvd.mailservice.service.Mail;
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
public class TicketCanceledMail implements Mail {

    private final Configuration configuration;
    private final JavaMailSender mailSender;

    @Override
    @SneakyThrows
    public Mono<Void> send(Map<String, Object> params) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Ticket canceled");
        helper.setTo(params.get("user.email").toString());
        String emailContent = getTicketCanceledMail(params);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
        return Mono.empty();
    }

    @SneakyThrows
    private String getTicketCanceledMail(Map<String, Object> params) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", params.get("user.name") + " " + params.get("user.surname"));
        model.put("ticket.tour.name", params.get("ticket.tour.name"));
        model.put("ticket.tour.country", params.get("ticket.tour.country"));
        model.put("ticket.tour.city", params.get("ticket.tour.city"));
        model.put("ticket.tour.arrivalTime", params.get("ticket.tour.arrivalTime"));
        configuration.getTemplate("canceled.ftlh")
                .process(model, stringWriter);
        return stringWriter.getBuffer()
                .toString();
    }

}
