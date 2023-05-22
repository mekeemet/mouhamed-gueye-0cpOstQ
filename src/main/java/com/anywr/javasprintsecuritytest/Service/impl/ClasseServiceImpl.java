package com.anywr.javasprintsecuritytest.Service.impl;

import com.anywr.javasprintsecuritytest.Entity.Classe;
import com.anywr.javasprintsecuritytest.Entity.Teacher;
import com.anywr.javasprintsecuritytest.Repository.ClasseRepository;
import com.anywr.javasprintsecuritytest.Repository.TeacherRepository;
import com.anywr.javasprintsecuritytest.Service.Interface.ClasseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ClasseServiceImpl implements ClasseService {
    private final ClasseRepository classeRepository;
    private final TeacherRepository teacherRepository;

    public ClasseServiceImpl(ClasseRepository classeRepository, TeacherRepository teacherRepository) {
        this.classeRepository = classeRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Page<Classe> getClasse(Pageable pageable) {
        return classeRepository.findAll(pageable);
    }

    @Override
    public List<Classe> getClasses() {
        return (List<Classe>) classeRepository.findAll();
    }


    @Override
    public Classe createClasse(Classe classe) {
        try {
            log.info("Creating a Classe");
            Optional<Teacher> teacher = teacherRepository.findById(classe.getTeacher().getId());
            if (teacher.isPresent()) {
                return classeRepository.save(classe);
            } else {
                throw new RuntimeException("No entity with given id: " + teacher.get().getId());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("unexpected error while creating Classe");
        }
    }

    @Override
    public Classe updateClasse(Classe classe, Long id) {
        try {
            Optional<Classe> classeOld = classeRepository.findById(id);
            if (classeOld.isPresent()) {
                Optional<Teacher> teacher = teacherRepository.findById(classe.getTeacher().getId());
                if (teacher.isPresent()) {
                    classeOld.get().setName(classe.getName());
                    classeOld.get().setTeacher(teacher.get());
                } else {
                    throw new RuntimeException("No entity with given id: " + id);
                }
            } else {
                throw new RuntimeException("No entity with given id: " + id);
            }
            log.info("updating the Classe, Id:" + id);
            return classeRepository.save(classeOld.get());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("unexpected error while Update Classe");
        }
    }

    @Override
    public void deleteClasse(Long id) {
        try {
            Optional<Classe> classeOld = classeRepository.findById(id);
            if (classeOld.isPresent()) {
                log.info("deleting the classe, Id:" + id);
                classeRepository.deleteById(classeOld.get().getId());
            } else {
                throw new RuntimeException("No entity with given id: " + id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("unexpected error while delete Classe");
        }
    }
}
