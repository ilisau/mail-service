package com.solvd.mailservice.web.mapper;

import com.solvd.mailservice.domain.MailData;
import com.solvd.mailservice.web.dto.MailDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MailDataMapper {

    /**
     * Converts entity to dto.
     *
     * @param entity entity
     * @return dto
     */
    MailDataDto toDto(MailData entity);

    /**
     * Converts dto to entity.
     *
     * @param userDto dto
     * @return entity
     */
    MailData toEntity(MailDataDto userDto);

}
