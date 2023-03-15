package com.solvd.mailservice.web.kafka;

import com.google.gson.Gson;
import com.solvd.mailservice.domain.MailData;
import com.solvd.mailservice.service.Mail;
import com.solvd.mailservice.web.dto.MailDataDto;
import com.solvd.mailservice.web.mapper.MailDataMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;

@Component
@RequiredArgsConstructor
public class MessageReceiverImpl implements MessageReceiver {

    private final KafkaReceiver<String, Object> receiver;
    private final Mail activationMail;
    private final Mail resetMail;
    private final Mail bookedTourMail;
    private final Mail ticketCanceledMail;
    private final MailDataMapper mailDataMapper;

    @PostConstruct
    public void init() {
        fetch();
    }

    @Override
    public void fetch() {
        receiver.receive()
                .subscribe(r -> {
                    //TODO make this more elegant
                    String json = (String) r.value();
                    MailDataDto mailDataDto = new Gson().fromJson(json, MailDataDto.class);
                    MailData mailData = mailDataMapper.toEntity(mailDataDto);
                    switch (mailData.getMailType()) {
                        case ACTIVATION -> activationMail.send(mailData.getParams()).subscribe();
                        case PASSWORD_RESET -> resetMail.send(mailData.getParams()).subscribe();
                        case BOOKED_TOUR -> bookedTourMail.send(mailData.getParams()).subscribe();
                        case TICKET_CANCELED -> ticketCanceledMail.send(mailData.getParams()).subscribe();
                    }
                    r.receiverOffset().acknowledge();
                });
    }

}
