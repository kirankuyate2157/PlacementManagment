package com.kiran.restapi.controller;

import com.kiran.restapi.entity.Certificate;
import com.kiran.restapi.entity.College;
import com.kiran.restapi.entity.Student;
import com.kiran.restapi.entity.User;
import com.kiran.restapi.repository.CertificateRepository;
import com.kiran.restapi.repository.CollegeRepository;
import com.kiran.restapi.repository.StudentRepository;
import com.kiran.restapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/certificates")
public class CertificateController {

	@Autowired
	private CertificateRepository certificateRepository;

	@Autowired
	private CollegeRepository collegeRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public ResponseEntity<List<Certificate>> getAllCertificates() {
	    List<Certificate> allCertificates = certificateRepository.findAll();

	    List<Certificate> modifiedCertificates = allCertificates.stream()
	            .map(cert -> {
	                Certificate modifiedCert = cert;
	                modifiedCert.getCollege().setCollege_admin(null);
	                return modifiedCert;
	            })
	            .collect(Collectors.toList());

	    return new ResponseEntity<>(modifiedCertificates, HttpStatus.OK);
	}


	@PostMapping("/add")
	public ResponseEntity<Object> addCertificate(@RequestBody Certificate certificate, @RequestParam long studentId,
			@RequestParam long collegeId, @RequestParam long adminId) {
		try {
			Optional<College> existingCollege = collegeRepository.findById(collegeId);
			if (!existingCollege.isPresent()) {
				return new ResponseEntity<>("College with provided ID is not present,please provide valid ID .",
						HttpStatus.BAD_REQUEST);
			}

			College college = existingCollege.get();
			Optional<Student> existingStudent = studentRepository.findById(studentId);
			if (!existingStudent.isPresent()) {
				return new ResponseEntity<>("Student  with provided ID is not Exist please provide valide ID.",
						HttpStatus.BAD_REQUEST);
			}
			
			Optional<User> existingAdmin = userRepository.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new ResponseEntity<>("Admin with provided ID is not associated with the college.",
						HttpStatus.BAD_REQUEST);
			}
			User admin = existingAdmin.get();
			// Check if the updated admin is the same as the current college admin
			if (college.getCollege_admin().getId() != admin.getId()) {
				// Admin are different, return an error response
				return new ResponseEntity<>("Invalid college_admin credentials. Admins do not match. with collge",
						HttpStatus.BAD_REQUEST);
			}
			
			Student std=existingStudent.get();
			certificate.setCollege(college);
			certificate.setStudent_name(std.getName());
			
			Certificate savedCertificate = certificateRepository.save(certificate);
			std.getCertificate().add(savedCertificate);
			studentRepository.save(std);
			return new ResponseEntity<>(savedCertificate, HttpStatus.CREATED);
			
		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return new ResponseEntity<>("Failed to Generate certificate. Check server logs for details.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PatchMapping("/update/{id}")
	public ResponseEntity<Object> updateCertificate(@PathVariable long id,@RequestBody Certificate certificate, @RequestParam long studentId,
			@RequestParam long collegeId, @RequestParam long adminId) {
		try {
			Optional<College> existingCollege = collegeRepository.findById(collegeId);
			if (!existingCollege.isPresent()) {
				return new ResponseEntity<>("College with provided ID is not present,please provide valid ID .",
						HttpStatus.BAD_REQUEST);
			}

			College college = existingCollege.get();
			Optional<Student> existingStudent = studentRepository.findById(studentId);
			if (!existingStudent.isPresent()) {
				return new ResponseEntity<>("Student  with provided ID is not Exist please provide valide ID.",
						HttpStatus.BAD_REQUEST);
			}
			
			Optional<User> existingAdmin = userRepository.findById(adminId);
			if (!existingAdmin.isPresent()) {
				return new ResponseEntity<>("Admin with provided ID is not associated with the college.",
						HttpStatus.BAD_REQUEST);
			}
			User admin = existingAdmin.get();
			// Check if the updated admin is the same as the current college admin
			if (college.getCollege_admin().getId() != admin.getId()) {
				// Admin are different, return an error response
				return new ResponseEntity<>("Invalid college_admin credentials. Admins do not match. with collge",
						HttpStatus.BAD_REQUEST);
			}
			
			
			Optional<Certificate> optionalCertificate = certificateRepository.findById(id);

	        if (optionalCertificate.isPresent()) {
	            Certificate certificateData = optionalCertificate.get();
	            certificateData.setCertificate_name(certificate.getCertificate_name());
	            Certificate updatedCertificate = certificateRepository.save(certificateData);
	            return new ResponseEntity<>(updatedCertificate, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Certificate with provided ID not found.", HttpStatus.NOT_FOUND);
	        }
			
		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return new ResponseEntity<>("Failed to Update certificate. Check server logs for details.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCertificateById(@PathVariable long id) {
		Optional<Certificate> optionalCertificate = certificateRepository.findById(id);

		if (optionalCertificate.isPresent()) {
			Certificate certificate = optionalCertificate.get();
			certificate.getCollege().setCollege_admin(null);
			return new ResponseEntity<>(certificate, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Certificate not found with the provided ID: " + id, HttpStatus.NOT_FOUND);
		}
	}


	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCertificate(@PathVariable long id, @RequestParam long studentId,
	        @RequestParam long collegeId, @RequestParam long adminId) {
	    try {
	        Optional<College> existingCollege = collegeRepository.findById(collegeId);
	        if (!existingCollege.isPresent()) {
	            return new ResponseEntity<>("College with provided ID is not present, please provide valid ID.",
	                    HttpStatus.BAD_REQUEST);
	        }

	        College college = existingCollege.get();
	        Optional<Student> existingStudent = studentRepository.findById(studentId);
	        if (!existingStudent.isPresent()) {
	            return new ResponseEntity<>("Student with provided ID does not exist, please provide valid ID.",
	                    HttpStatus.BAD_REQUEST);
	        }

	        Optional<User> existingAdmin = userRepository.findById(adminId);
	        if (!existingAdmin.isPresent()) {
	            return new ResponseEntity<>("Admin with provided ID is not associated with the college.",
	                    HttpStatus.BAD_REQUEST);
	        }
	        User admin = existingAdmin.get();
	        // Check if the updated admin is the same as the current college admin
	        if (college.getCollege_admin().getId() != admin.getId()) {
	            // Admins are different, return an error response
	            return new ResponseEntity<>("Invalid college_admin credentials. Admins do not match with college.",
	                    HttpStatus.BAD_REQUEST);
	        }

	        Optional<Certificate> optionalCertificate = certificateRepository.findById(id);

	        if (optionalCertificate.isPresent()) {
	            Certificate certificate = optionalCertificate.get();
	            // Remove the certificate from the associated student
	            Student student = existingStudent.get();
	            student.getCertificate().remove(certificate);
	            studentRepository.save(student);


	            // Delete the certificate
	            certificateRepository.deleteById(id);

	            return new ResponseEntity<>("Certificate deleted successfully.", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Certificate with provided ID not found.", HttpStatus.NOT_FOUND);
	        }

	    } catch (Exception e) {
	        // Log the exception for debugging purposes
	        e.printStackTrace();
	        return new ResponseEntity<>("Failed to delete certificate. Check server logs for details.",
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
