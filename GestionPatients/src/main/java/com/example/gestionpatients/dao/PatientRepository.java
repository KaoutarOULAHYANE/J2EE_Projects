package com.example.gestionpatients.dao;

import com.example.gestionpatients.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    public Page<Patient> findByNameContains(String name, Pageable pageable);
    @Query("select p from Patient p where p.name like :x and p.dateNaissance = :y")
    public Page<Patient> chercher(
            @Param("x") String name,
            @Param("y") Date date,
            Pageable pageable);
}
