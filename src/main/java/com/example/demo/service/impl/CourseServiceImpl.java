package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.GeneratedId;
import com.example.demo.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public boolean existsByUniversityId(String universityId) {
        return courseRepository.existsCourseByUniversity_UniversityId(universityId);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findByUniversityId(String universityId) {
        return courseRepository.findAllByUniversity_UniversityId(universityId);
    }

    @Override
    public <S extends Course> S save(S entity) {
        if (entity.getCourseId()==null){
            Course lastCourse = findTopByOrderByIdDesc();
            String previousID = lastCourse == null ? "C000001" : lastCourse.getCourseId();
            entity.setCourseId(GeneratedId.getGeneratedId(previousID));
        }
        return courseRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course findTopByOrderByIdDesc() {
        return courseRepository.findTopByOrderByCourseIdDesc();
    }

    @Override
    public Optional<Course> findByName(String name) {
        return courseRepository.findByCourseName(name);
    }

    @Override
    public Optional<Course> findById(String id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findByNameContaining(String name) {
        return courseRepository.findByCourseNameContaining(name);
    }
}
