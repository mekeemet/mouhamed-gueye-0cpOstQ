package com.anywr.javasprintsecuritytest.Mapper;

import com.anywr.javasprintsecuritytest.Entity.Classe;
import com.anywr.javasprintsecuritytest.Entity.Teacher;
import com.anywr.javasprintsecuritytest.Entity.Student;
import com.anywr.javasprintsecuritytest.EntityDto.ClasseDto;
import com.anywr.javasprintsecuritytest.EntityDto.TeacherDto;
import com.anywr.javasprintsecuritytest.EntityDto.StudentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
  // #region entity to dto

  StudentDto studentToStudentDto(Student student);

  TeacherDto teacherToTeacherDto(Teacher teacher);

  ClasseDto classeToClasseDto(Classe classe);

  // #endregion
  // #region dto to entity

  Student studentDtoToStudent(StudentDto studentDto);

  Teacher teacherDtoToTeacher(TeacherDto teacherDto);

  Classe classeDtoToClasse(ClasseDto classeDto);

  // #endregion

}
