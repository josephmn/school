package com.api.school.student.domain.ports;

import com.openapi.generate.model.ResponseExampleDto;
import com.openapi.generate.model.ResponseStudentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * StudentRepositoryPort.
 *
 * @author Joseph Magallanes
 * @since 2025-08-27
 */
public interface StudentRepositoryPort {
    Flux<ResponseStudentDto> getAllStudents();
    Mono<ResponseStudentDto> getStudentById(Integer id);
    Mono<ResponseExampleDto> getStudentObjectById(Integer id);
}
