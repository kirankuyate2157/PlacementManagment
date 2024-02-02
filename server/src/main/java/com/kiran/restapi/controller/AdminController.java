package com.kiran.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kiran.restapi.entity.Admin;
import com.kiran.restapi.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin") // Base path for all endpoints@RequestMapping("/student") // Base path for all
							// endpoints
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	@GetMapping("/")
	public ResponseEntity<List<Admin>> getAllAdmin() {
		List<Admin> Admins = adminRepository.findAll();
		return new ResponseEntity<>(Admins, HttpStatus.OK);
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void createAdmin(@RequestBody Admin admin) {
		adminRepository.save(admin);
	}

	@PutMapping("/update")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
		Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());

		if (optionalAdmin.isPresent()) {
			Admin existingAdmin = optionalAdmin.get();
			existingAdmin.setName(admin.getName());
			existingAdmin.setPassword(admin.getPassword());

			adminRepository.save(existingAdmin);
			return new ResponseEntity<>(existingAdmin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable long id) {
		Optional<Admin> optionalAdmin = adminRepository.findById(id);
		return optionalAdmin.map(admin -> new ResponseEntity<>(admin, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable long id) {
		Optional<Admin> optionalAdmin = adminRepository.findById(id);
		if (optionalAdmin.isPresent()) {
			adminRepository.delete(optionalAdmin.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Admin> loginAdmin(@RequestBody Admin loginAdmin) {
		Optional<Admin> optionalAdmin = adminRepository.findByNameAndPassword(loginAdmin.getName(),
				loginAdmin.getPassword());

		return optionalAdmin.map(admin -> new ResponseEntity<>(admin, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/logout")
	public ResponseEntity<Boolean> logoutAdmin(@RequestBody Admin logoutAdmin) {
		Optional<Admin> optionalAdmin = adminRepository.findByNameAndPassword(logoutAdmin.getName(),
				logoutAdmin.getPassword());

		if (optionalAdmin.isPresent()) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

}
