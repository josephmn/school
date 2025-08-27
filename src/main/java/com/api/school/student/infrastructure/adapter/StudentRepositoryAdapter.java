package com.api.school.student.infrastructure.adapter;

import org.springframework.stereotype.Service;
import com.api.school.exception.types.AlreadyExistsException;
import com.api.school.student.domain.ports.StudentRepositoryPort;
import com.api.school.student.infrastructure.repository.StudentRepositoryReactive;
import com.api.school.util.StudentMapper;
import com.openapi.generate.model.ResponseStudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * StudentRepositoryAdapter.
 *
 * @author Joseph Magallanes
 * @since 2024-08-27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepositoryPort {

    private final StudentRepositoryReactive studentRepositoryReactive;
    private final StudentMapper studentMapper;

    @Override
    public Flux<ResponseStudentDto> getAllStudents() {
        log.info("Start execute method getAllStudents");
        return studentRepositoryReactive.findAll()
            .map(studentMapper::studentToResponse)
            .doOnTerminate(() -> log.info("Finished execute method getAllStudents"));
    }

    @Override
    public Mono<ResponseStudentDto> getStudentById(Integer id) {
        log.info("Start execute method getStudentById");
        return studentRepositoryReactive.findById(id)
            .map(studentMapper::studentToResponse)
            .switchIfEmpty(Mono.error(new AlreadyExistsException(
                "Student not found with id: %s", id)))
                .doOnTerminate(() -> log.info("Finished execute method getStudentById"));
    }
}
