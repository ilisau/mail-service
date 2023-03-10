package com.solvd.mailservice.web.mapper;

import com.solvd.mailservice.domain.MailData;
import com.solvd.mailservice.web.dto.MailDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MailDataMapper {

    MailDataDto toDto(MailData entity);

    MailData toEntity(MailDataDto userDto);

}
