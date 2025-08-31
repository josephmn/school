package com.api.school.student.domain.ports;

import com.openapi.generate.model.ResponseExampleDto;
import com.openapi.generate.model.ResponseStudentDto;
import com.openapi.generate.model.ResponseStudentSimpleDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * StudentRepositoryPort.
 *
 * @author Joseph Magallanes
 * @since 2025-08-27
 */
public interface StudentRepositoryPort {
    Flux<ResponseStudentSimpleDto> getAllStudentsSimple();
    Flux<ResponseStudentDto> getAllStudents();
    Mono<ResponseStudentSimpleDto> getStudentByIdSimple(Integer id);
    Mono<ResponseStudentDto> getStudentById(Integer id);
    Mono<ResponseExampleDto> getStudentObjectById(Integer id);
}
