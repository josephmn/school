package com.api.school.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.api.school.student.infrastructure.entity.StudentEntity;
import com.openapi.generate.model.ResponseExampleDto;

/**
 * ExampleMapper.
 *
 * @author Joseph Magallanes
 * @since 2025-08-27
 */
@Mapper(componentModel = "spring")
public interface ExampleMapper {

    /**
     * Maps a StudentEntity to a ResponseExampleDto.
     * @param student the StudentEntity to map
     * @return the mapped ResponseExampleDto
     */
    @Mappings({
        @Mapping(source = "id", target = "code"),
        @Mapping(source = "people.documentType", target = "typeDocument"),
        @Mapping(source = "people.documentNumber", target = "numberDocument"),
        @Mapping(source = "people.firstName", target = "nameFirst"),
        @Mapping(source = "people.lastName", target = "nameLast"),
        @Mapping(source = "people.birthDate", target = "dateBirth"),
        @Mapping(source = "people.gender", target = "gender"),
        @Mapping(source = "people.address", target = "address"),
        @Mapping(source = "people.phone", target = "phone"),
        @Mapping(source = "people.email", target = "email"),
        @Mapping(source = "entryDate", target = "dateEntry"),
        @Mapping(source = "status", target = "status"),
        @Mapping(source = "observations", target = "observations")
    })
    ResponseExampleDto studentToResponse(StudentEntity student);
}
