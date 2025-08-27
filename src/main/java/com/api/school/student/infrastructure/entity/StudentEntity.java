package com.api.school.student.infrastructure.entity;

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
@JsonIgnoreProperties({"peopleId"})
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentEntity {
    @Id
    private Integer id;
    private Integer peopleId;
    private LocalDate entryDate;
    private Boolean status;
    private String observations;
    @Transient
    private PeopleEntity people;
}
