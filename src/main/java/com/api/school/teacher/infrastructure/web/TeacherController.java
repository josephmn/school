package com.api.school.teacher.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import com.api.school.teacher.domain.ports.TeacherRepositoryPort;
import com.openapi.generate.api.TeachersApi;
import com.openapi.generate.model.ResponseTeacherDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * TeacherController.
 *
 * @author Joseph Magallanes
 * @since 2024-08-29
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TeacherController implements TeachersApi {

    private final TeacherRepositoryPort teacherRepositoryPort;

    @Override
    public Mono<ResponseEntity<Flux<ResponseTeacherDto>>> getAllTeacher(
        ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(this.teacherRepositoryPort.getAllTeachers()))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<ResponseTeacherDto>> getTeacherById(
        Integer id,
        ServerWebExchange exchange) {
        return this.teacherRepositoryPort.getTeacherById(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
