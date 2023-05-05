package com.solvd.mailservice.web.controller;

import com.solvd.mailservice.domain.MailData;
import com.solvd.mailservice.service.Mail;
import com.solvd.mailservice.web.dto.MailDataDto;
import com.solvd.mailservice.web.dto.validation.OnCreate;
import com.solvd.mailservice.web.mapper.MailDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/emails")
@RequiredArgsConstructor
public class MailController {

    private final Mail activationMail;
    private final Mail resetMail;
    private final Mail bookedTourMail;
    private final Mail ticketCanceledMail;
    private final MailDataMapper mailDataMapper;

    /**
     * Sends email.
     *
     * @param mailDataDto dto
     * @return empty response
     */
    @PostMapping
    public Mono<Void> send(
            @Validated(OnCreate.class)
            @RequestBody final MailDataDto mailDataDto
    ) {
        //TODO make this more elegant
        MailData mailData = mailDataMapper.toEntity(mailDataDto);
        return switch (mailData.getMailType()) {
            case ACTIVATION -> activationMail.send(mailData.getParams());
            case PASSWORD_RESET -> resetMail.send(mailData.getParams());
            case BOOKED_TOUR -> bookedTourMail.send(mailData.getParams());
            case TICKET_CANCELED -> ticketCanceledMail.send(
                    mailData.getParams()
            );
        };
    }

}
