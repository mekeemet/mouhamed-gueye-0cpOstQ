package com.anywr.javasprintsecuritytest.Repository;

import com.anywr.javasprintsecuritytest.Entity.Personn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnRepository extends JpaRepository<Personn,Long> {
    @Query(value = "select * from personn where username=:username",nativeQuery = true)
    Personn findByUsername(@Param("username") String username);
}
