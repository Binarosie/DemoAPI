package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByCourseNameContaining(String name);

    List<Course> findByCourseId(String Id);

    Boolean existsCourseByUniversity_UniversityId(String universityId);

    List<Course> findAllByUniversity_UniversityId(String universityId);

    List<Course> findAll();

    Course findTopByOrderByCourseIdDesc();

    Optional<Course> findByCourseName(String courseName);
}
