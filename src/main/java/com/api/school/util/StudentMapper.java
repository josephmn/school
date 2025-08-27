package com.api.school.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.api.school.student.infrastructure.entity.StudentEntity;
import com.openapi.generate.model.ResponseStudentDto;

/**
 * StudentMapper.
 * This interface defines methods to map between RequestDto, ResponseDto, and StudentEntity.
 * It uses MapStruct for the mapping implementation.
 *
 * @author Joseph Magallanes
 * @since 2025-08-27
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {

    /**
     * Maps a StudentEntity to a ResponseStudentDto.
     * @param student the StudentEntity to map
     * @return the mapped ResponseStudentDto
     */
    @Mappings({
        @Mapping(source = "id", target = "code"),
        @Mapping(source = "peopleId", target = "peopleCode"),
        @Mapping(source = "entryDate", target = "dateEntry"),
        @Mapping(source = "status", target = "status"),
        @Mapping(source = "observations", target = "observations")
    })
    ResponseStudentDto studentToResponse(StudentEntity student);
}
