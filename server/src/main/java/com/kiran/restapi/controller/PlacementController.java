package com.kiran.restapi.controller;

import com.kiran.restapi.entity.Placement;
import com.kiran.restapi.entity.Student;
import com.kiran.restapi.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/placement")
public class PlacementController {

	@Autowired
	private PlacementRepository placementRepository;

	@GetMapping("/")
	public ResponseEntity<List<Placement>> getAllPlacement() {
		List<Placement> placements = placementRepository.findAll();
		return new ResponseEntity<>(placements, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Placement> addPlacement(@RequestBody Placement placement) {
		Placement savedPlacement = placementRepository.save(placement);
		return new ResponseEntity<>(savedPlacement, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Placement> updatePlacement(@RequestBody Placement placement) {
		if (placementRepository.existsById(placement.getId())) {
			Placement updatedPlacement = placementRepository.save(placement);
			return new ResponseEntity<>(updatedPlacement, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getPlacementById(@PathVariable long id) {
		Optional<Placement> optionalPlacement = placementRepository.findById(id);
		return optionalPlacement.map(placement -> new ResponseEntity<>(placement, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>("Placement not found", HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> cancelPlacement(@PathVariable long id) {
		if (placementRepository.existsById(id)) {
			placementRepository.deleteById(id);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

}
