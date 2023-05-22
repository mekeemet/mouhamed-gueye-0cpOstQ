package com.anywr.javasprintsecuritytest.Service.impl;

import com.anywr.javasprintsecuritytest.Entity.Teacher;
import com.anywr.javasprintsecuritytest.Repository.ClasseRepository;
import com.anywr.javasprintsecuritytest.Repository.TeacherRepository;
import com.anywr.javasprintsecuritytest.Service.Interface.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ClasseRepository classeRepository;


    public TeacherServiceImpl(TeacherRepository teacherRepository, ClasseRepository classeRepository) {
        this.teacherRepository = teacherRepository;
        this.classeRepository = classeRepository;
    }


    @Override
    public Page<Teacher> getTeacher(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        try {
            log.info("Creating a Teacher");
            return teacherRepository.save(teacher);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("unexpected error while creating Teacher");
        }
    }

    @Override
    public Teacher updateTeacher(Teacher teacher, Long id) {
        try {
            Optional<Teacher> teacherOld = teacherRepository.findById(id);
            if (teacherOld.isPresent()) {
                teacherOld.get().setFirstName(teacher.getFirstName());
                teacherOld.get().setLastName(teacher.getLastName());
            } else {
                throw new RuntimeException("No entity with given id: " + id);
            }
            log.info("updating the Teacher, Id:" + id);
            return teacherRepository.save(teacherOld.get());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("unexpected error while Update Teacher");
        }
    }

    @Override
    public void deleteTeacher(Long id) {
        try {
            Optional<Teacher> enseignantOld = teacherRepository.findById(id);
            if (enseignantOld.isPresent()) {
                log.info("deleting the Teacher, Id:" + id);
                teacherRepository.deleteById(enseignantOld.get().getId());
            } else {
                throw new RuntimeException("No entity with given id: " + id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("unexpected error while delete Teacher");
        }
    }
}
