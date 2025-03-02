package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.GeneratedId;
import com.example.demo.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements IUniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public List<University> findAll() { return universityRepository.findAll(); }

    @Override
    public <S extends University> S save(S entity){
        if (entity.getUniversityId()==null){
            University lastUniversity = findTopByOrderByIdDesc();
            String previousID = lastUniversity == null ? "U000001" : lastUniversity.getUniversityId();
            entity.setUniversityId(GeneratedId.getGeneratedId(previousID));
        }
        return universityRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        universityRepository.deleteById(id);
    }

    @Override
    public void delete(University entity) {
        universityRepository.delete(entity);
    }

    @Override
    public List<University> findByUniversityNameContaining(String universityName) {
        return universityRepository.findByUniversityNameContaining(universityName);
    }

    @Override
    public List<University> findById(String id){
        return universityRepository.findByUniversityId(id);
    }

    @Override
    public Optional<University> findByUniversityId(String id){
        return universityRepository.findById(id);
    }

    @Override
    public University findTopByOrderByIdDesc() {
        return universityRepository.findTopByOrderByUniversityIdDesc();
    }

    @Override
    public Optional<University> findByName(String universityName) {
        return universityRepository.findByUniversityName(universityName);
    }

}
