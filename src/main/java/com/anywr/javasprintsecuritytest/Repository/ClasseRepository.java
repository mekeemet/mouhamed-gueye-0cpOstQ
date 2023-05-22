package com.anywr.javasprintsecuritytest.Repository;

import com.anywr.javasprintsecuritytest.Entity.Classe;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends PagingAndSortingRepository<Classe, Long> {}
