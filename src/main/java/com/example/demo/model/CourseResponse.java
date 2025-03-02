package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseResponse {
    private String courseId;
    private String courseName;
    private String description;
    private String universityId;
    private String universityName;
}
