package com.anywr.javasprintsecuritytest.Repository;

import com.anywr.javasprintsecuritytest.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    @Query(value = "select * from searchStudent(:classeName,:teacherLastName,:teacherFirstName)", nativeQuery = true)
    Page<Student> findStudentByMultiCritere(@Param("classeName") String classeName,
                                            @Param("teacherLastName") String teacherLastName,
                                            @Param("teacherFirstName") String teacherFirstName,
                                            Pageable pageable);

}
