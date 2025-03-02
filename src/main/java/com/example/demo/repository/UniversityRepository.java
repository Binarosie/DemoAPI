package com.example.demo.repository;

import com.example.demo.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, String> {
    List<University> findAll();

    List<University> findByUniversityNameContaining(String universityName);

    List<University> findByUniversityId(String universityId);

    Optional<University> findById(String universityId);

    University findTopByOrderByUniversityIdDesc();

    Optional<University> findByUniversityName(String universityName);

}
