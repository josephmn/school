package com.api.school.teacher.domain.ports;

import com.openapi.generate.model.ResponseTeacherDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * TeacherRepositoryPort.
 *
 * @author Joseph Magallanes
 * @since 2025-08-29
 */
public interface TeacherRepositoryPort {
    Flux<ResponseTeacherDto> getAllTeachers();
    Mono<ResponseTeacherDto> getTeacherById(Integer id);
}
