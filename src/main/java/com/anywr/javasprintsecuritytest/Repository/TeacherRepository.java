package com.anywr.javasprintsecuritytest.Repository;

import com.anywr.javasprintsecuritytest.Entity.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends PagingAndSortingRepository<Teacher,Long> {
}
