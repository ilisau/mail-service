package com.solvd.mailservice.web.dto;

import com.solvd.mailservice.domain.MailType;
import lombok.Data;

import java.util.Map;

@Data
public class MailDataDto {

    private MailType mailType;
    private Map<String, Object> params;

}
