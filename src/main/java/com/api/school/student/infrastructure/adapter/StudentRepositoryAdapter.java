package com.api.school.student.infrastructure.adapter;

import org.springframework.stereotype.Service;
import com.api.school.exception.types.AlreadyExistsException;
import com.api.school.people.infrastructure.repository.PeopleRepositoryReactive;
import com.api.school.student.domain.ports.StudentRepositoryPort;
import com.api.school.student.infrastructure.repository.StudentRepositoryReactive;
import com.api.school.util.ExampleMapper;
import com.api.school.util.PeopleMapper;
import com.api.school.util.StudentMapper;
import com.openapi.generate.model.ResponseExampleDto;
import com.openapi.generate.model.ResponseStudentDto;
import com.openapi.generate.model.ResponseStudentSimpleDto;
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
    private final PeopleRepositoryReactive peopleRepositoryReactive;
    private final PeopleMapper peopleMapper;
    private final StudentMapper studentMapper;
    private final ExampleMapper exampleMapper;

    @Override
    public Flux<ResponseStudentSimpleDto> getAllStudentsSimple() {
        log.info("Start execute method getAllStudentsSimple");
        return studentRepositoryReactive.findAll()
            .switchIfEmpty(Mono.error(new AlreadyExistsException("Students response simple data not found")))
            .map(studentMapper::studentToSimpleResponse)
            .doOnTerminate(() -> log.info("Finished execute method getAllStudentsSimple"));
    }

    @Override
    public Flux<ResponseStudentDto> getAllStudents() {
        log.info("Start execute method getAllStudents");
        return studentRepositoryReactive.findAll()
            .switchIfEmpty(Mono.error(new AlreadyExistsException("Students data not found")))
            .flatMap(student -> {
                if (student.getPeopleId() == null) {
                    return Mono.just(studentMapper.studentToResponse(student));
                }
                return peopleRepositoryReactive.findById(student.getPeopleId())
                    .map(people -> {
                        final ResponseStudentDto dto = studentMapper.studentToResponse(student);
                        dto.setPeople(peopleMapper.peopleToResponse(people));
                        return dto;
                    })
                    .defaultIfEmpty(studentMapper.studentToResponse(student));
            })
            .doOnTerminate(() -> log.info("Finished execute method getAllStudents"));
    }

    @Override
    public Mono<ResponseStudentSimpleDto> getStudentByIdSimple(Integer id) {
        log.info("Start execute method getStudentByIdSimple");
        return studentRepositoryReactive.findById(id)
            .switchIfEmpty(Mono.error(new AlreadyExistsException(
                "Student not found with id: %s", id)))
            .map(studentMapper::studentToSimpleResponse)
            .doOnTerminate(() -> log.info("Finished execute method getStudentByIdSimple"));
    }

    @Override
    public Mono<ResponseStudentDto> getStudentById(Integer id) {
        log.info("Start execute method getStudentById");
        return studentRepositoryReactive.findById(id)
            .switchIfEmpty(Mono.error(new AlreadyExistsException(
                "Student not found with id: %s", id)))
            .flatMap(student -> {
                if (student.getPeopleId() == null) {
                    return Mono.just(studentMapper.studentToResponse(student));
                }
                return peopleRepositoryReactive.findById(student.getPeopleId())
                    .map(people -> {
                        final ResponseStudentDto dto = studentMapper.studentToResponse(student);
                        dto.setPeople(peopleMapper.peopleToResponse(people));
                        return dto;
                    })
                    .defaultIfEmpty(studentMapper.studentToResponse(student));
            })
            .doOnTerminate(() -> log.info("Finished execute method getStudentById"));
    }

    @Override
    public Mono<ResponseExampleDto> getStudentObjectById(Integer id) {
        log.info("Start execute method getStudentObjectById");
        return studentRepositoryReactive.findById(id)
            .switchIfEmpty(Mono.error(new AlreadyExistsException(
                "Student not found with id: %s", id)))
            .flatMap(student -> {
                if (student.getPeopleId() == null) {
                    return Mono.just(studentMapper.studentToResponse(student));
                }
                return peopleRepositoryReactive.findById(student.getPeopleId())
                    .map(people -> {
                        final ResponseExampleDto dto = exampleMapper.studentToResponse(student);
                        dto.setTypeDocument(people.getDocumentType());
                        dto.setNumberDocument(people.getDocumentNumber());
                        dto.setNameFirst(people.getFirstName());
                        dto.setNameLast(people.getLastName());
                        dto.setDateBirth(people.getBirthDate());
                        dto.setGender(people.getGender().toString());
                        dto.setAddress(people.getAddress());
                        dto.setPhone(people.getPhone());
                        dto.setEmail(people.getEmail());
                        return dto;
                    })
                    .defaultIfEmpty(exampleMapper.studentToResponse(student));
            })
            .cast(ResponseExampleDto.class)
            .doOnTerminate(() -> log.info("Finished execute method getStudentObjectById"));
    }
}
