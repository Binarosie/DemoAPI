package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.model.Response;
import com.example.demo.service.ICourseService;
import com.example.demo.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UniversityController {
    @Autowired
    private IUniversityService universityService;

    @Autowired
    ICourseService courseService;

    @GetMapping("/universities")
    public ResponseEntity<?> getAllUniversities() {
        return new ResponseEntity<Response>(new Response(true, "Success",
                universityService.findAll()), HttpStatus.OK);
    }

    @PostMapping(path = "/getUniversity")
    public ResponseEntity<?> getUniversity(@Validated @RequestParam("universityId") String id) {
        Optional<University> university = universityService.findByUniversityId(id);
        if (university.isPresent()) {
            return new ResponseEntity<Response>(new Response(true, "Success", university.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(new Response(false, "Không tìm thấy trường đại học nào!", null), HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping(path = "/getUniversityName")
    public ResponseEntity<?> getUniversityName(@Validated @RequestParam("universityName") String name) {
        List<University> universityList = universityService.findByUniversityNameContaining(name);
        if (universityList.isEmpty()) {
            return new ResponseEntity<Response>(new Response(false, "Không tìm thấy trường đại học nào!", null), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Response>(new Response(true, "Success", universityList), HttpStatus.OK);
        }
    }

    //ham kiem tra ten van chua tot, add duoc dai hoc cung ten
    @PostMapping(path = "/addUniversity")
    public ResponseEntity<?> addUniversity(@Validated @RequestParam("name") String universityName,
                                           @RequestParam("address") String universityAddress,
                                           @RequestParam("createdOn") String createdOn) {
        Optional<University> optUniversity = universityService.findByName(universityName);
        if (optUniversity.isPresent()) {
            return new ResponseEntity<Response>(new Response(false, "Truong dai hoc da ton tai", optUniversity.get()), HttpStatus.BAD_REQUEST);
        } else {
            University university = new University();
            university.setUniversityName(universityName);
            university.setUniversityAddress(universityAddress);
            university.setCreateOn(createdOn);
            universityService.save(university);
            return new ResponseEntity<>(new Response(true, "Success", university), HttpStatus.OK);
        }
    }

    // xoa thanh cong ma message tra ve null
    @DeleteMapping(path = "/deleteUniversity")
    public ResponseEntity<?> deleteUniversity(@Validated @RequestParam("universityId")
                                              String universityId) {
        Optional<University> university = universityService.findByUniversityId(universityId);
        if (university.isEmpty()) {
            return new ResponseEntity<Response>(new Response(false, "Không tìm thấy trường đại học nào!", null), HttpStatus.NOT_FOUND);
        }
        if (courseService.existsByUniversityId(universityId)) {
            return new ResponseEntity<Response>(new Response(false, "Không thể xóa! Trường đại học đang có khóa học liên kết", null), HttpStatus.BAD_REQUEST);
        }

        universityService.deleteById(universityId);
        return new ResponseEntity<>(new Response(true, "Success", null), HttpStatus.OK);
    }
}
