package com.anywr.javasprintsecuritytest.EntityDto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchStudentDto {
    private String classeName;

    private String teacherFirstName;

    private String teacherLastName;

    private int page;

    private int perPage;

    private String orderBy;

    private String direction;
}
