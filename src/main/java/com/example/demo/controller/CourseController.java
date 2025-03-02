package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.model.CourseResponse;
import com.example.demo.model.Response;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.ICourseService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private ICourseService courseService;

//    @GetMapping("/courses")
//    public ResponseEntity<?> getAllCourses() {
//        return new ResponseEntity<Response>(new Response(true, "Success",
//                courseService.findAll()), HttpStatus.OK);
//    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses() {
        List<CourseResponse> responseList = courseService.findAll().stream()
                .map(course -> new CourseResponse(
                        course.getCourseId(),
                        course.getCourseName(),
                        course.getDescription(),
                        course.getUniversity().getUniversityId(),
                        course.getUniversity().getUniversityName()
                ))
                .toList();

        return new ResponseEntity<Response>(new Response(true, "Success",responseList), HttpStatus.OK);
    }


    @PostMapping(path = "/getCourse")
    public ResponseEntity<?> getCourse(@Validated @RequestParam("courseId") String id) {
        Optional<Course> course = courseService.findById(id);
        if (course.isPresent()) {
            return new ResponseEntity<Response>(new Response(true, "Success", course.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(new Response(false, "Không tìm thấy khoas học nào!", null), HttpStatus.NOT_FOUND);

        }
    }
}
