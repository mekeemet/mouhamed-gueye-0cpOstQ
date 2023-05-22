package com.anywr.javasprintsecuritytest.Service.Interface;

import com.anywr.javasprintsecuritytest.Entity.Student;
import com.anywr.javasprintsecuritytest.EntityDto.SearchStudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
  Page<Student> getStudent(Pageable pageable);

  Page<Student> searchStudent(SearchStudentDto searchStudentDto,Pageable pageable);

  Student createStudent(Student student);

  Student updateStudent(Student student, Long id);

  void deleteStudent(Long id);
}
