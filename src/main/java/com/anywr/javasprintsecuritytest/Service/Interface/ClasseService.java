package com.anywr.javasprintsecuritytest.Service.Interface;

import com.anywr.javasprintsecuritytest.Entity.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClasseService {
  Page<Classe> getClasse(Pageable pageable);

  List<Classe>getClasses();

  Classe createClasse(Classe classe);

  Classe updateClasse(Classe classe, Long id);

  void deleteClasse(Long id);

}
