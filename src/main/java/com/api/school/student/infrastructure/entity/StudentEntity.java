package com.api.school.student.infrastructure.entity;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StudentEntity.
 *
 * @author Joseph Magallanes
 * @since 2025-08-27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class StudentEntity {
    @Id
    private Integer id;
    private Integer peopleId;
    private LocalDate entryDate;
    private Boolean status;
    private String observations;
}
