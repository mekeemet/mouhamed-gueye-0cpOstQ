package com.anywr.javasprintsecuritytest.Service.impl;

import com.anywr.javasprintsecuritytest.Entity.Student;
import com.anywr.javasprintsecuritytest.EntityDto.SearchStudentDto;
import com.anywr.javasprintsecuritytest.Repository.StudentRepository;
import com.anywr.javasprintsecuritytest.Service.Interface.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> getStudent(Pageable pageable)
    {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> searchStudent(SearchStudentDto searchStudentDto, Pageable pageable) {
        Page<Student> students=studentRepository.findStudentByMultiCritere(
                searchStudentDto.getClasseName(),
                searchStudentDto.getTeacherLastName(),
                searchStudentDto.getTeacherFirstName(),
                pageable);
        return students;

    }

    @Override
    public Student createStudent(Student student) {
        try {
            log.info("Creating a Student");
            return studentRepository.save(student);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("unexpected error while creating Student");
        }
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        try {
            Optional<Student> etudiantOld = studentRepository.findById(id);
            if (etudiantOld.isPresent()) {
                etudiantOld.get().setFirstName(student.getFirstName());
                etudiantOld.get().setLastName(student.getLastName());
                etudiantOld.get().setClasse(student.getClasse());
            } else {
                throw new RuntimeException("No entity with given id: " + id);
            }
            log.info("updating the Student, Id:" + id);
            return studentRepository.save(etudiantOld.get());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("unexpected error while Update Student");
        }
    }

    @Override
    public void deleteStudent(Long id) {
        try {
            Optional<Student> etudiantOld = studentRepository.findById(id);
            if (etudiantOld.isPresent()) {
                log.info("deleting the etudiant, Id:" + id);
                studentRepository.deleteById(etudiantOld.get().getId());
            } else {
                throw new RuntimeException("No entity with given id: " + id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("unexpected error while delete Student");
        }
    }
}
