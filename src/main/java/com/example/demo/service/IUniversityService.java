package com.example.demo.service;

import com.example.demo.entity.University;

import java.util.List;
import java.util.Optional;

public interface IUniversityService {
    List<University> findAll();

    <S extends University> S save(S entity);

    void deleteById(String id);

    void delete(University entity);

    List<University> findByUniversityNameContaining(String universityName);

    List<University> findById(String id);

    Optional<University> findByUniversityId(String id);

    University findTopByOrderByIdDesc();

    Optional<University> findByName(String universityName);
}
