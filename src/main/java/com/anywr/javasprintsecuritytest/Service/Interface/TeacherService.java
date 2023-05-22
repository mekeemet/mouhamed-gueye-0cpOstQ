package com.anywr.javasprintsecuritytest.Service.Interface;

import com.anywr.javasprintsecuritytest.Entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
  Page<Teacher> getTeacher(Pageable pageable);

  Teacher createTeacher(Teacher teacher);

  Teacher updateTeacher(Teacher teacher, Long id);

  void deleteTeacher(Long id);
}
