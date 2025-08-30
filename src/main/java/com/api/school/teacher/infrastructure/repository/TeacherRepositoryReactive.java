package com.api.school.teacher.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.api.school.teacher.infrastructure.entity.TeacherEntity;

/**
 * TeacherRepositoryReactive.
 *
 * @author Joseph Magallanes
 * @since 2024-08-29
 */
@Repository
public interface TeacherRepositoryReactive extends ReactiveCrudRepository<TeacherEntity, Integer> {
}
