package com.example.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", length = 7)
    @Size(max = 7)
    private String courseId;

    @Column(name = "CourseName", columnDefinition = "NVARCHAR(255)", nullable = false)
    @NotEmpty(message = "Course name is required")
    private String courseName;

    @Column(name = "Description", columnDefinition = "NVARCHAR(255)", nullable = false)
    @NotEmpty(message = "Description is required")
    private String description;

    @ManyToOne
    @JoinColumn(name="universityId", nullable = false)
    private University university;
}
