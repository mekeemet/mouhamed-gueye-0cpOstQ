package com.anywr.javasprintsecuritytest.Repository;

import com.anywr.javasprintsecuritytest.Entity.Personn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnRepository extends JpaRepository<Personn,Long> {
    public Personn findByUsername(String username);
}
