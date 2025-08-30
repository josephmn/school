package com.api.school.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.api.school.teacher.infrastructure.entity.TeacherEntity;
import com.openapi.generate.model.ResponseTeacherDto;

/**
 * TeacherMapper.
 * This class is intended to define mapping methods between Teacher-related DTOs and entities.
 * Currently, it does not contain any mapping methods.
 *
 * @author Joseph Magallanes
 * @since 2025-08-29
 */
@Mapper(componentModel = "spring")
public interface TeacherMapper {

    /**
     * Maps a TeacherEntity to a ResponseTeacherDto.
     * @param teacher the TeacherEntity to map
     * @return the mapped ResponseTeacherDto
     */
    @Mappings({
        @Mapping(source = "id", target = "code"),
//        @Mapping(source = "peopleId", target = "peopleCode"),
        @Mapping(source = "specialty", target = "specialty"),
        @Mapping(source = "entryDate", target = "dateEntry"),
        @Mapping(source = "contractType", target = "typeContract"),
        @Mapping(source = "salary", target = "salary"),
        @Mapping(source = "status", target = "status"),
        @Mapping(source = "people", target = "people")
    })
    ResponseTeacherDto teacherToResponse(TeacherEntity teacher);
}
