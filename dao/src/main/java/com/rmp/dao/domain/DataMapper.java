package com.rmp.dao.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides common data mapper.
 */
@Mapper
public interface DataMapper {

    @Named("timestampConverter")
    default LocalDateTime timestampConverter(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date == null || date.isEmpty() ?
                null :
                LocalDateTime.parse(date, formatter);
    }
}
