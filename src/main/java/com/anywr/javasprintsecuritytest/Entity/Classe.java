package com.anywr.javasprintsecuritytest.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Classe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "teacherId", nullable = false)
    private Teacher teacher;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, //orphanRemoval = true,
            mappedBy = "classe")
    private Set<Student> students = new HashSet<>();

}

