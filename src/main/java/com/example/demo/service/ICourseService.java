package com.example.demo.service;

import com.example.demo.entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    boolean existsByUniversityId(String universityId);
    List<Course> findAll();
    List<Course> findByUniversityId(String universityId);
    <S extends Course> S save(S entity);
    void deleteById(String id);
    Course findTopByOrderByIdDesc();
    Optional<Course> findByName(String name);
    Optional<Course> findById(String id);
    List<Course> findByNameContaining(String name);
}
