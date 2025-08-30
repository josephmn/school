package com.api.school.teacher.infrastructure.adapter;

import org.springframework.stereotype.Service;
import com.api.school.exception.types.AlreadyExistsException;
import com.api.school.people.infrastructure.repository.PeopleRepositoryReactive;
import com.api.school.teacher.domain.ports.TeacherRepositoryPort;
import com.api.school.teacher.infrastructure.repository.TeacherRepositoryReactive;
import com.api.school.util.PeopleMapper;
import com.api.school.util.TeacherMapper;
import com.openapi.generate.model.ResponseTeacherDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * TeacherRepositoryAdapter.
 *
 * @author Joseph Magallanes
 * @since 2024-08-29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherRepositoryAdapter implements TeacherRepositoryPort {

    private final TeacherRepositoryReactive teacherRepositoryReactive;
    private final PeopleRepositoryReactive peopleRepositoryReactive;
    private final TeacherMapper teacherMapper;
    private final PeopleMapper peopleMapper;

    @Override
    public Flux<ResponseTeacherDto> getAllTeachers() {
        log.info("Start execute method getAllTeachers");
        return teacherRepositoryReactive.findAll()
            .switchIfEmpty(Mono.error(new AlreadyExistsException("Teachers not found data")))
            .flatMap(teacher -> {
                if (teacher.getPeopleId() == null) {
                    return Mono.just(teacherMapper.teacherToResponse(teacher));
                }
                return peopleRepositoryReactive.findById(teacher.getPeopleId())
                    .map(people -> {
                        final ResponseTeacherDto dto = teacherMapper.teacherToResponse(teacher);
                        dto.setPeople(peopleMapper.peopleToResponse(people));
                        return dto;
                    })
                    .defaultIfEmpty(teacherMapper.teacherToResponse(teacher));
            })
            .doOnTerminate(() -> log.info("Finished execute method getAllTeachers"));
    }

    @Override
    public Mono<ResponseTeacherDto> getTeacherById(Integer id) {
        log.info("Start execute method getTeacherById");
        return teacherRepositoryReactive.findById(id)
            .switchIfEmpty(Mono.error(new AlreadyExistsException(
                "Teacher not found with id: %s", id)))
            .flatMap(teacher -> {
                if (teacher.getPeopleId() == null) {
                    return Mono.just(teacherMapper.teacherToResponse(teacher));
                }
                return peopleRepositoryReactive.findById(teacher.getPeopleId())
                    .map(people -> {
                        final ResponseTeacherDto dto = teacherMapper.teacherToResponse(teacher);
                        dto.setPeople(peopleMapper.peopleToResponse(people));
                        return dto;
                    })
                    .defaultIfEmpty(teacherMapper.teacherToResponse(teacher));
            })
            .doOnTerminate(() -> log.info("Finished execute method getTeacherById"));
    }
}
