package com.anywr.javasprintsecuritytest.EntityDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private ClasseDto classe;
}
