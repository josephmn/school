package com.api.school.student.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.api.school.student.infrastructure.entity.StudentEntity;

/**
 * StudentRepositoryReactive.
 *
 * @author Joseph Magallanes
 * @since 2024-08-27
 */
@Repository
public interface StudentRepositoryReactive extends ReactiveCrudRepository<StudentEntity, Integer> {
}
