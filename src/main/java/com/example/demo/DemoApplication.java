package com.example.demo;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    CommandLineRunner initDatabase(UniversityRepository universityRepository, CourseRepository courseRepository) {
        return args -> {
            // Chèn dữ liệu mẫu cho University trước
            University oxford = new University("U000001", "Oxford University", "Oxford, UK", "1096-01-01");
            University cambridge = new University("U000002", "Cambridge University", "Cambridge, UK", "1209-01-01");
            University tokyo = new University("U000003", "Tokyo University", "Tokyo, Japan", "1877-04-12");

            universityRepository.saveAll(List.of(oxford, cambridge, tokyo));

            System.out.println("✅ Inserted Sample Universities into Database");

            // Chèn dữ liệu mẫu cho Course
            courseRepository.saveAll(List.of(
                    new Course("C000001", "Computer Science", "Study of computation and programming", oxford),
                    new Course("C000002", "Mathematics", "Advanced calculus and algebra", oxford),
                    new Course("C000003", "Physics", "Fundamentals of quantum mechanics", cambridge),
                    new Course("C000004", "Artificial Intelligence", "Machine learning and deep learning", cambridge),
                    new Course("C000005", "Mechanical Engineering", "Study of machines and thermodynamics", tokyo),
                    new Course("C000006", "Japanese Language", "Advanced Japanese proficiency", tokyo)
            ));

            System.out.println("✅ Inserted Sample Courses into Database");
        };
    }

}

