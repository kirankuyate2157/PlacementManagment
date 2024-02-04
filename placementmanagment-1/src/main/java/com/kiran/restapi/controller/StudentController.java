package com.kiran.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kiran.restapi.entity.Certificate;
import com.kiran.restapi.entity.College;
import com.kiran.restapi.entity.Student;
import com.kiran.restapi.entity.User;
import com.kiran.restapi.repository.CertificateRepository;
import com.kiran.restapi.repository.CollegeRepository;
import com.kiran.restapi.repository.StudentRepository;
import com.kiran.restapi.repository.UserRepository;
import com.kiran.restapi.utils.*;
import java.util.*;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student") // Base path for all endpoints
public class StudentController {

    @Autowired
    StudentRepository repo;
    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    CollegeRepository collegeRepository;
    @Autowired
    UserRepository  userRepository;

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Optional<Student> optionalStudent = repo.findById(id);
        return optionalStudent.map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        // Check if a student with the same roll number already exists
        Optional<Student> existingStudent = repo.findByRollno(student.getRollno());
        
        if (existingStudent.isPresent()) {
            // Student with the same roll number already exists
            return new ResponseEntity<>("Student with the given roll number already exists", HttpStatus.BAD_REQUEST);
        } else {
            // Save the new student if no duplicate exists
            repo.save(student);
            return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
        }
    }

    
    
    private <T> T getUpdatedValue(T currentValue, T updatedValue) {
        return updatedValue != null ? updatedValue : currentValue;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student updatedStudent) {
        return repo.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(getUpdatedValue(existingStudent.getName(), updatedStudent.getName()));
                    existingStudent.setRollno(getUpdatedValue(existingStudent.getRollno(), updatedStudent.getRollno()));
                    existingStudent.setCollege(getUpdatedValue(existingStudent.getCollege(), updatedStudent.getCollege()));
                    existingStudent.setQualification(getUpdatedValue(existingStudent.getQualification(), updatedStudent.getQualification()));
                    existingStudent.setCouerse(getUpdatedValue(existingStudent.getCouerse(), updatedStudent.getCouerse()));
                    existingStudent.setYear(getUpdatedValue(existingStudent.getYear(), updatedStudent.getYear()));
                    existingStudent.setCertificate(getUpdatedValue(existingStudent.getCertificate(), updatedStudent.getCertificate()));
                    existingStudent.setHallticket(getUpdatedValue(existingStudent.getHallticket(), updatedStudent.getHallticket()));
                    existingStudent.setPercentage(getUpdatedValue(existingStudent.getPercentage(), updatedStudent.getPercentage()));
                    existingStudent.setBranch(getUpdatedValue(existingStudent.getBranch(), updatedStudent.getBranch()));

                    repo.save(existingStudent);
                    return new ResponseEntity<>(existingStudent, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> removeStudent(@PathVariable long id) {
        Optional<Student> optionalStudent = repo.findById(id);
        if (optionalStudent.isPresent()) {
            repo.delete(optionalStudent.get());
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Student deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Student not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    
    @GetMapping("/hallticket/{ticketNo}")
    public ResponseEntity<Object> getStudentByHallTicket(@PathVariable String ticketNo) {
        Optional<Student> optionalStudent = repo.findByHallticket(ticketNo);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return ResponseEntity.ok(new ApiResponse("success", student, "Student found"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("error", null, "Student not found"));
        }
    }

    @GetMapping("/findByYearAndCollege")
    public List<Student> getStudentsByYearAndCollege(@RequestParam int year, @RequestParam String college) {
        return repo.findByYearAndCollege(year, college);
    }

	/*
	 * @PostMapping("/{id}/certificates/add") public ResponseEntity<Certificate>
	 * addCertificateForStudent(@PathVariable long id, @RequestBody Certificate
	 * certificate) { Optional<Student> optionalStudent = repo.findById(id);
	 * 
	 * if (optionalStudent.isPresent()) { Student student = optionalStudent.get();
	 * 
	 * // Check if the certificate already exists Optional<Certificate>
	 * existingCertificate =
	 * certificateRepository.findByYearAndCollege(certificate.getYear(),
	 * certificate.getCollege());
	 * 
	 * if (existingCertificate.isPresent()) { // Use the existing certificate
	 * student.getCertificate().add(existingCertificate.get()); } else { // Save the
	 * new certificate and associate it with the student Certificate
	 * savedCertificate = certificateRepository.save(certificate);
	 * student.getCertificate().add(savedCertificate); }
	 * 
	 * repo.save(student); return new ResponseEntity<>(certificate,
	 * HttpStatus.CREATED); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 */
    
    
    @PostMapping("/certificates/add")
    public ResponseEntity<Certificate> addCertificate(@RequestBody Certificate certificate,@RequestParam long studentId,@RequestParam long collegeId, @RequestParam long adminId) {
        // Check if the college with the given ID exists
        Optional<College> existingCollege = collegeRepository.findById(collegeId);

        if (existingCollege.isPresent()) {
            College college = existingCollege.get();

            // Check if the admin with the given ID is associated with the college
            User admin = college.getCollege_admin();

            if (admin != null && admin.getId() == adminId) {
                // Admin exists and is associated with the college, associate the certificate with the college and save
                certificate.setCollege(college);
                Certificate savedCertificate = certificateRepository.save(certificate);

                // Update and save the student (if necessary)
                // This step depends on how your Student entity is structured
                // Assuming there is a Student entity with a Set<Certificate> certificates field
                // You may need to adjust this part based on your actual entity structure
                Optional<Student> optionalStudent = repo.findById(studentId);

                if (optionalStudent.isPresent()) {
                    Student student = optionalStudent.get();
                    student.getCertificate().add(savedCertificate);
                    repo.save(student);
                }

                return new ResponseEntity<>(savedCertificate, HttpStatus.CREATED);
            } else {
                // Admin doesn't exist or is not associated with the college, handle accordingly (e.g., return an error response)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            // College doesn't exist, handle accordingly (e.g., return an error response)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
