package com.api.school.student.domain.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ExampleDto.
 *
 * @author Joseph Magallanes
 * @since 2025-08-27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleDto {
    private Integer code;
    private String typeDocument;
    private String numberDocument;
    private String nameFirst;
    private String nameLast;
    private LocalDate dateBirth;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private LocalDate dateEntry;
    private Boolean status;
    private String observations;
}
