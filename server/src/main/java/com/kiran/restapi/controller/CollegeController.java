package com.kiran.restapi.controller;

import com.kiran.restapi.entity.College;
import com.kiran.restapi.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CollegeController {

    @Autowired
    CollegeRepository collegeRepository;
    
    @Autowired
    private PlacementController placementRepository;

    @PostMapping("/colleges/add")
    public ResponseEntity<College> addCollege(@RequestBody College college) {
        collegeRepository.save(college);
        return new ResponseEntity<>(college, HttpStatus.CREATED);
    }

    @PutMapping("/colleges/update")
    public ResponseEntity<College> updateCollege(@RequestBody College updatedCollege) {
        Optional<College> optionalCollege = collegeRepository.findById(updatedCollege.getId());

        if (optionalCollege.isPresent()) {
            College college = optionalCollege.get();
            college.setCollege_admin(updatedCollege.getCollege_admin());
            college.setCollege_name(updatedCollege.getCollege_name());
            college.setLocation(updatedCollege.getLocation());

            collegeRepository.save(college);
            return new ResponseEntity<>(college, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/colleges/{id}")
    public ResponseEntity<Object> getCollege(@PathVariable long id) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        return optionalCollege.map(college -> new ResponseEntity<>(college, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("College not found", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/colleges/{id}")
    public ResponseEntity<Boolean> deleteCollege(@PathVariable long id) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        if (optionalCollege.isPresent()) {
            collegeRepository.delete(optionalCollege.get());
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/placements/schedule")
    public ResponseEntity<Boolean> schedulePlacement(@PathVariable long id, @RequestBody Placement placement) {
        Optional<College> optionalCollege = collegeRepository.findById(id);

        if (optionalCollege.isPresent()) {
            College college = optionalCollege.get();
            placement.setCollege(college);  // Set the college for the placement
            placementRepository.save(placement);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
    
}
