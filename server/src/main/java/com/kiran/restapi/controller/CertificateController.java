package com.kiran.restapi.controller;

import com.kiran.restapi.entity.Certificate;
import com.kiran.restapi.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/certificate") // Base path for all endpoints
public class CertificateController {

	@Autowired
	private CertificateRepository certificateRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Certificate>> getAllCertificates() {
		List<Certificate> allCertificates = certificateRepository.findAll();
		return new ResponseEntity<>(allCertificates, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Certificate> addCertificate(@RequestBody Certificate certificate) {
		Certificate savedCertificate = certificateRepository.save(certificate);
		return new ResponseEntity<>(savedCertificate, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Certificate> updateCertificate(@RequestBody Certificate certificate) {
		Optional<Certificate> optionalCertificate = certificateRepository.findById(certificate.getId());

		if (optionalCertificate.isPresent()) {
			Certificate existingCertificate = optionalCertificate.get();
			existingCertificate.setYear(certificate.getYear());
			existingCertificate.setCollege(certificate.getCollege());

			Certificate updatedCertificate = certificateRepository.save(existingCertificate);
			return new ResponseEntity<>(updatedCertificate, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Certificate> getCertificateById(@PathVariable long id) {
		Optional<Certificate> optionalCertificate = certificateRepository.findById(id);

		return optionalCertificate.map(certificate -> new ResponseEntity<>(certificate, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCertificate(@PathVariable long id) {
		Optional<Certificate> optionalCertificate = certificateRepository.findById(id);

		if (optionalCertificate.isPresent()) {
			certificateRepository.deleteById(id);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}
}
