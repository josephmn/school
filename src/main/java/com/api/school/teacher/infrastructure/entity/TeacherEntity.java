package com.api.school.teacher.infrastructure.entity;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import com.api.school.people.infrastructure.entity.PeopleEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TeacherEntity.
 *
 * @author Joseph Magallanes
 * @since 2025-08-29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teachers")
@JsonIgnoreProperties({"peopleId"})
public class TeacherEntity {
    @Id
    private Integer id;
    private Integer peopleId;
    private String specialty;
    private LocalDate entryDate;
    private String contractType;
    private Double salary;
    private Boolean status;
    @Transient
    private PeopleEntity people;
}
