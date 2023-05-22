package com.anywr.javasprintsecuritytest.EntityDto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ClasseDto implements Serializable {
    private Long id;
    private String name;
    private TeacherDto teacher;
}
