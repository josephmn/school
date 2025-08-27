package com.api.school.people.infrastructure.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PeopleEntity.
 *
 * @author Joseph Magallanes
 * @since 2025-08-26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "peoples")
public class PeopleEntity {
    @Id
    private Integer id;
    private String documentType;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private LocalDateTime registerDate;
}
