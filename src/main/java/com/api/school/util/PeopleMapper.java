package com.api.school.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import com.api.school.people.infrastructure.entity.PeopleEntity;
import com.openapi.generate.model.RequestPeopleDto;
import com.openapi.generate.model.ResponsePeopleDto;

/**
 * PeopleMapper.
 * This interface defines methods to map between RequestDto, ResponseDto, and PeopleEntity.
 * It uses MapStruct for the mapping implementation.
 *
 * @author Joseph Magallanes
 * @since 2025-06-16
 */
@Mapper(componentModel = "spring")
public interface PeopleMapper {

    /**
     * Maps a RequestDto to a PeopleEntity.
     * @param request the RequestDto to map
     * @return the mapped PeopleEntity
     */
    @Mappings({
        @Mapping(source = "typeDocument", target = "documentType"),
        @Mapping(source = "numberDocument", target = "documentNumber"),
        @Mapping(source = "nameFirst", target = "firstName"),
        @Mapping(source = "nameLast", target = "lastName"),
        @Mapping(source = "dateBirth", target = "birthDate"),
        @Mapping(source = "gender", target = "gender"),
        @Mapping(source = "address", target = "address"),
        @Mapping(source = "phone", target = "phone"),
        @Mapping(source = "email", target = "email")
    })
    PeopleEntity requestToPeople(RequestPeopleDto request);

    /**
     * Maps a PeopleEntity to a ResponseDto.
     * @param people the PeopleEntity to map
     * @return the mapped ResponseDto
     */
    @Mappings({
        @Mapping(source = "id", target = "code"),
        @Mapping(source = "documentType", target = "typeDocument"),
        @Mapping(source = "documentNumber", target = "numberDocument"),
        @Mapping(source = "firstName", target = "nameFirst"),
        @Mapping(source = "lastName", target = "nameLast"),
        @Mapping(source = "birthDate", target = "dateBirth"),
        @Mapping(source = "gender", target = "gender"),
        @Mapping(source = "address", target = "address"),
        @Mapping(source = "phone", target = "phone"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "createdDate", target = "dateCreated", qualifiedByName = "localDateTimeToString"),
        @Mapping(source = "modifiedDate", target = "dateModified", qualifiedByName = "localDateTimeToString")
    })
    ResponsePeopleDto peopleToResponse(PeopleEntity people);

    /**
     * Maps a String to a LocalDate.
     * @param localDateTime the String to map
     * @return the mapped localDateTime atOffset ZoneOffset UTC
     *         date_register:
     *           type: string
     *           format: date-time
     *           example: '2025-08-02 07:48:56'
     *           description: Date when the people was registered
     */
    @Named("localDateTimeToOffsetDateTimeUTC")
    default OffsetDateTime localDateTimeToOffsetDateTimeUTC(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atOffset(ZoneOffset.UTC);
    }

    /**
     * Maps a LocalDateTime to a String in the format "yyyy-MM-dd HH:mm:ss".
     * @param localDateTime the LocalDateTime to map
     * @return the formatted String
     */
    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        // Mantener el formato de la BD: "2025-08-02 07:48:56"
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    /**
     * Maps a String in the format "yyyy-MM-dd HH:mm:ss" to a LocalDateTime.
     * @param dateString the String to map
     * @return the parsed LocalDateTime
     * @throws IllegalArgumentException if the input string is not in the expected format
     */
    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            return LocalDateTime.parse(dateString, formatter);
        }
        catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido: " + dateString +
                ". Se espera: yyyy-MM-dd HH:mm:ss", e);
        }
    }

    /**
     * Maps a LocalDateTime to a String in ISO format without timezone.
     * @param localDateTime the LocalDateTime to map
     * @return the formatted String in ISO format
     */
    @Named("localDateTimeToISOString")
    default String localDateTimeToISOString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        // Formato ISO sin timezone: "2025-08-02T07:48:56"
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
