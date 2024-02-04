package com.kiran.restapi.controller;

import com.kiran.restapi.entity.College;
import com.kiran.restapi.entity.Placement;
import com.kiran.restapi.entity.Student;
import com.kiran.restapi.entity.User;
import com.kiran.restapi.repository.CollegeRepository;
import com.kiran.restapi.repository.PlacementRepository;
import com.kiran.restapi.repository.StudentRepository;
import com.kiran.restapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/placements")
public class PlacementController {

	@Autowired
	private PlacementRepository placementRepository;

	@Autowired
	private CollegeRepository collegeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Placement>> getAllPlacement() {
		try {
			List<Placement> placements = placementRepository.findAll();

			if (placements.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content if the list is empty
			} else {
				List<Placement> modifiedPlacements = placements.stream().map(place -> {
					Placement modifiedPlace = place;
					modifiedPlace.getCollege().setCollege_admin(null);
					return modifiedPlace;
				}).collect(Collectors.toList());

				return new ResponseEntity<>(modifiedPlacements, HttpStatus.OK);
			}

		} catch (Exception e) {
			// Log the exception or handle it as needed
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 Internal Server Error for other
																			// exceptions
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addPlacement(@RequestBody Placement placement, @RequestParam long collegeId,
			@RequestParam long adminId) {
		try {
			Optional<College> existingCollege = collegeRepository.findById(collegeId);

			if (!existingCollege.isPresent()) {
				return new ResponseEntity<>("College with provided ID does not exist.", HttpStatus.BAD_REQUEST);
			}

			College college = existingCollege.get();

			// Check if the admin with the given ID is associated with the college
			if (college.getCollege_admin() != null && college.getCollege_admin().getId() == adminId) {
				placement.setCollege(college);
				Placement savedPlacement = placementRepository.save(placement);
				savedPlacement.getCollege().setCollege_admin(null);
				return new ResponseEntity<>(savedPlacement, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Admin with provided ID is not associated with the college.",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return new ResponseEntity<>("Failed to create placement. Check server logs for details.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Object> updatePlacement(@PathVariable long id, @RequestBody Placement updatedPlacement,
			@RequestParam long collegeId, @RequestParam long adminId) {
		try {
			Optional<College> existingCollege = collegeRepository.findById(collegeId);
			if (!existingCollege.isPresent()) {
				return new ResponseEntity<>("College with provided ID is not present, please provide a valid ID.",
						HttpStatus.BAD_REQUEST);
			}

			College college = existingCollege.get();

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

			Optional<Placement> optionalPlacement = placementRepository.findById(id);

			if (optionalPlacement.isPresent()) {
				Placement placementData = optionalPlacement.get();

				// Update only the fields that are present in the request body
				if (updatedPlacement.getName() != null) {
					placementData.setName(updatedPlacement.getName());
				}
				if (updatedPlacement.getDate() != null) {
					placementData.setDate(updatedPlacement.getDate());
				}
				if (updatedPlacement.getQualification() != null) {
					placementData.setQualification(updatedPlacement.getQualification());
				}
				if (updatedPlacement.getCompany_name() != null) {
					placementData.setCompany_name(updatedPlacement.getCompany_name());
				}
				if (updatedPlacement.getCompany_description() != null) {
					placementData.setCompany_description(updatedPlacement.getCompany_description());
				}
				if (updatedPlacement.getJob_position() != null) {
					placementData.setJob_position(updatedPlacement.getJob_position());
				}

				// Save the updated placement data
				placementRepository.save(placementData);

				return new ResponseEntity<>("Placement updated successfully.", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Placement with provided ID not found.", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return new ResponseEntity<>("Failed to update Placement. Check server logs for details.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{placementId}/addParticipant/{studentId}")
	public ResponseEntity<Object> addParticipantToPlacement(@PathVariable long placementId,
			@PathVariable long studentId) {
		try {
			Optional<Placement> optionalPlacement = placementRepository.findById(placementId);
			Optional<Student> optionalStudent = studentRepository.findById(studentId);

			if (optionalPlacement.isPresent() && optionalStudent.isPresent()) {
				Placement placement = optionalPlacement.get();
				Student student = optionalStudent.get();

				// Check if the student is not already a participant
				if (!placement.getParticipants().contains(student)) {
					placement.getParticipants().add(student);
					placementRepository.save(placement);

					return new ResponseEntity<>("Student added to placement participants successfully.", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Student is already a participant in this placement.",
							HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>("Placement or Student with provided ID not found.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return new ResponseEntity<>("Failed to add participant to placement. Check server logs for details.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{placementId}/removeParticipant/{studentId}")
    public ResponseEntity<Object> removeParticipantFromPlacement(@PathVariable long placementId, @PathVariable long studentId) {
        try {
            Optional<Placement> optionalPlacement = placementRepository.findById(placementId);
            Optional<Student> optionalStudent = studentRepository.findById(studentId);

            if (optionalPlacement.isPresent() && optionalStudent.isPresent()) {
                Placement placement = optionalPlacement.get();
                Student student = optionalStudent.get();

                // Check if the student is a participant
                if (placement.getParticipants().contains(student)) {
                    placement.getParticipants().remove(student);
                    placementRepository.save(placement);

                    return new ResponseEntity<>("Student removed from placement participants successfully.", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Student is not a participant in this placement.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Placement or Student with provided ID not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>("Failed to remove participant from placement. Check server logs for details.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	
	@DeleteMapping("/delete/{placementId}")
	public ResponseEntity<Object> deletePlacement(@PathVariable long placementId, @RequestParam long collegeId, @RequestParam long adminId) {
	    try {
	        Optional<College> existingCollege = collegeRepository.findById(collegeId);

	        if (!existingCollege.isPresent()) {
	            return new ResponseEntity<>("College with provided ID does not exist.", HttpStatus.BAD_REQUEST);
	        }

	        College college = existingCollege.get();

	        // Check if the admin with the given ID is associated with the college
	        if (college.getCollege_admin() != null && college.getCollege_admin().getId() == adminId) {
	            Optional<Placement> optionalPlacement = placementRepository.findById(placementId);

	            if (optionalPlacement.isPresent()) {
	                Placement placementToDelete = optionalPlacement.get();

	                // Ensure that the placement belongs to the specified college
	                if (placementToDelete.getCollege().getId() == collegeId) {
	                	// Remove all participants from the placement
	                	placementToDelete.getParticipants().clear();
	                    placementRepository.save(placementToDelete);
	                    placementRepository.delete(placementToDelete);
	                    
	                    return new ResponseEntity<>("Placement deleted successfully.", HttpStatus.OK);
	                } else {
	                    return new ResponseEntity<>("Placement does not belong to the specified college.",
	                            HttpStatus.BAD_REQUEST);
	                }
	            } else {
	                return new ResponseEntity<>("Placement with provided ID does not exist.", HttpStatus.NOT_FOUND);
	            }
	        } else {
	            return new ResponseEntity<>("Admin with provided ID is not associated with the college.",
	                    HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        // Log the exception for debugging purposes
	        e.printStackTrace();
	        return new ResponseEntity<>("Failed to delete placement. Check server logs for details.",
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	
	
}


