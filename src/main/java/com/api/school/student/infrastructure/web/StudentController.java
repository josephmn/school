package com.api.school.student.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import com.api.school.student.domain.ports.StudentRepositoryPort;
import com.openapi.generate.api.StudentsApi;
import com.openapi.generate.model.ResponseStudentDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * StudentController.
 *
 * @author Joseph Magallanes
 * @since 2024-08-27
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudentController implements StudentsApi {

    private final StudentRepositoryPort studentRepositoryPort;

    @Override
    public Mono<ResponseEntity<Flux<ResponseStudentDto>>> getAllStudent(
        ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(this.studentRepositoryPort.getAllStudents()))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<ResponseStudentDto>> getStudentById(Integer id, ServerWebExchange exchange) {
        return this.studentRepositoryPort.getStudentById(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
